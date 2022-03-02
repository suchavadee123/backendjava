package com.pj.ad.repository.core;

import java.io.Serializable;



import org.springframework.data.domain.Sort.Direction;

import com.pj.ad.utils.CoreUtils;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Sort implements Serializable {

	private static final long serialVersionUID = 1L;
	private String colId;
	private String sort;
	
	public static Sort by(String colId, Direction direction) {
		Sort sort = new Sort();
		sort.setColId(colId);
		sort.setSort(direction.toString());
		return sort;
	}

	public String toSql(String alias) {
		return String.format(" %s\"%s\" %s ", 
				(CoreUtils.isNotEmpty(alias) ? alias + "." : ""),
				colId,
				(CoreUtils.isNotEmpty(sort) ? sort.toUpperCase() : "ASC"));
	}
	
	public static interface SqlOrderByProcessor {
		public String process(String alias, Sort sort);
	}
	
	public static class DefaultSqlOrderByProcessor implements SqlOrderByProcessor {

		@Override
		public String process(String alias, Sort sort) {
			return sort.toSql(alias);
		}
		
	}
}
