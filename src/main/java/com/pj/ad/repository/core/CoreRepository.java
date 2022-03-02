package com.pj.ad.repository.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import com.google.gson.Gson;
import com.pj.ad.utils.CoreUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly = true)
public class CoreRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public static final String PAGING_CLAUSE_NAME = "pagin_clause__";
	public static final String ROWNUM_COLUMN = "\"rowNo\"";

	@Autowired
	public CoreRepository(@Qualifier("second-db") DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public GridData searchPagingGridData(String pagingSql, SqlParams params) {
		return searchPagingGridData(pagingSql, null, params);
	}

	public GridData searchPagingGridData(String pagingSql, SqlParams params, Map<String, String> withClauses) {
		return searchPagingGridData(pagingSql, null, params, withClauses);
	}

	public GridData searchPagingGridData(String pagingSql, String outputSql, SqlParams params) {
		return searchPagingGridData(pagingSql, outputSql, params, null);
	}

	public GridData searchPagingGridData(String pagingSql, String outputSql, SqlParams params,
			Map<String, String> withClauses) {
		StringBuilder statement = new StringBuilder();

		statement.append("WITH sql_paging__ AS (").append(pagingSql).append("), ");
		statement.append(" temp_count__ AS ( SELECT COUNT(1) AS \"max_row_\" FROM sql_paging__ ), ");
		statement.append(PAGING_CLAUSE_NAME).append(" AS ( ");
		statement.append("		SELECT a.* FROM (");
		statement.append("			SELECT a.* FROM ( ");
		statement.append("  	  		SELECT a.*, ROW_NUMBER() OVER() ").append(ROWNUM_COLUMN).append(" FROM (");
		statement.append("    	    		SELECT * FROM sql_paging__, temp_count__) a ");
		statement.append("  	  		) a WHERE a.\"rowNo\" < :offset_ + :limit_ + 1 ) a");
		statement.append("		 WHERE \"rowNo\" >= :offset_ + 1");
		statement.append(" ) ");

		if (CoreUtils.isNotEmpty(withClauses)) {
			for (Map.Entry<String, String> clause : withClauses.entrySet()) {
				statement.append(",").append(clause.getKey()).append(" AS (").append(clause.getValue()).append(")");
			}
		}

		if (CoreUtils.isNotEmpty(outputSql)) {
			statement.append(outputSql);
		} else {
			statement.append(" SELECT * FROM ").append(PAGING_CLAUSE_NAME).append(" ORDER BY ").append(ROWNUM_COLUMN)
					.append(" ");
		}

		return queryPagingGridData(statement.toString(), params);
	}

	private GridData queryPagingGridData(String sql, SqlParams params) {
		List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql, params.getParams());
		long total = 0;
		if (result != null && !result.isEmpty()) {
			total = Long.valueOf(String.valueOf(result.get(0).get("max_row_")));
			for (Map<String, Object> map : result) {
				map.remove("max_row_");
			}
		}

		return GridData.of(result, total);
	}

	public GridData searchGridData(String sql, SqlParams params) {
		List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql.toString(), params.getParams());
		return GridData.of(result, result.size());
	}

	public Data getData(String sql, SqlParams params) {
		return Data.of(this.jdbcTemplate.queryForList(sql, params.getParams()));
	}

	public <T> T getData(String sql, SqlParams params, Class<T> obj) {
		try {
			if (obj.equals(String.class) || obj.equals(Integer.class) || obj.equals(Boolean.class)) {
				return this.jdbcTemplate.queryForObject(sql, params.getParams(), obj);
			}
			return this.jdbcTemplate.queryForObject(sql, params.getParams(), new BeanPropertyRowMapper<T>(obj));
		} catch (EmptyResultDataAccessException e) {
			if (obj.equals(String.class) || obj.equals(Integer.class) || obj.equals(Boolean.class)) {
				return null;
			}
			return new Gson().fromJson("{}", obj);
		}
	}

	public <T> List<T> getDataList(String sql, SqlParams params, Class<T> obj) {
		try {
			if (obj.equals(String.class) || obj.equals(Integer.class) || obj.equals(Boolean.class)) {
				return this.jdbcTemplate.queryForList(sql, params.getParams(), obj);
			}
			return this.jdbcTemplate.query(sql, params.getParams(), new BeanPropertyRowMapper<T>(obj));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	public Boolean update(String sql, SqlParams params) {
		return this.jdbcTemplate.update(sql, params.getParams()) > 0;
	}

	public Boolean delete(String sql, SqlParams params) {
		return this.jdbcTemplate.update(sql, params.getParams()) > 0;
	}

	public Integer getCountRows(String sql, SqlParams params) {
		try {
			return this.jdbcTemplate.queryForObject(sql, params.getParams(), Integer.class).intValue();
		} catch (EmptyResultDataAccessException e) {
			return new Integer(0);
		}
	}

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

}
