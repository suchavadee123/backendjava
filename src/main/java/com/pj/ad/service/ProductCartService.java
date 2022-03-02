package com.pj.ad.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pj.ad.entity.Address;
import com.pj.ad.entity.Product;
import com.pj.ad.entity.ProductCart;
import com.pj.ad.model.Admin00SaveModel;
import com.pj.ad.model.Admin00SearchModel;
import com.pj.ad.model.User00SaveModel;
import com.pj.ad.repository.AddressRepository;
import com.pj.ad.repository.ProductCartRepository;
import com.pj.ad.repository.ProductRepository;
import com.pj.ad.repository.core.CoreRepository;
import com.pj.ad.repository.core.GridData;
import com.pj.ad.repository.core.Sort;
import com.pj.ad.repository.core.SqlParams;
import com.pj.ad.service.ProductCartDetailModel.GetDetail;
import com.pj.ad.repository.core.PageModel;
import com.pj.ad.utils.CoreUtils;
import com.pj.ad.utils.Response;

//@Transactional
@Service
public class ProductCartService {
	
	@Autowired
	private CoreRepository coreRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	private ProductCartRepository productcartRepository;
	
	private ProductRepository productRepository;

	public GridData search(ProductCartSearchModel model) {
		SqlParams params = SqlParams.create(model);
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.* FROM (  ");
		sql.append(" SELECT row_number() OVER (ORDER BY p2.history_id) AS \"rowNum\" ");
		sql.append(" , p.price AS \"price\" ");
		sql.append(" , p.product_name AS \"productName\" ");
		sql.append(" , p2.\"comment\"  AS \"comment\" ");
		sql.append(" , p2.count  AS \"count\" ");
		sql.append(" , p2.sweet AS \"sweet\" ");
		sql.append(" , p2.history_id  AS \"historyId\" ");
		sql.append(" FROM productcart p2 ");
		sql.append(" LEFT join product p  on p.product_id = p2.product_id  ");
		sql.append(" WHERE 1=1 ");

		sql.append(" ) a ");
		sql.append(model.generateSqlOrderBy("a",
				Sort.by(model.getSorts().get(0).getColId(), Direction.fromString(model.getSorts().get(0).getSort()))));

		return coreRepository.searchPagingGridData(sql.toString(), params);
	}
	
//	public void cancel(GetDetail model) throws Exception{
//		productcartRepository.deleteById(model.historyId);
//	}
	
	
	public ProductCartDetailModel getDetail(ProductCartDetailModel model) {
		StringBuilder sql = new StringBuilder();
		SqlParams paramsDetail = SqlParams.create();
		sql.append(" SELECT ");
		sql.append(" p.price AS \"price\" ");
		sql.append(" , p.product_name AS \"productName\" ");
		sql.append(" , p2.product_id AS \"productd\" ");
		sql.append(" , p2.\"comment\"  AS \"comment\" ");
		sql.append(" , p2.count  AS \"count\" ");
		sql.append(" , p2.sweet AS \"sweet\" ");
		sql.append(" , p2.history_id AS \"historyId\" ");
		sql.append(" FROM productcart p2 ");
		sql.append(" LEFT join product p  on p2.product_id = p.product_id  ");
		sql.append(" WHERE p2.history_id = :historyId ");
		paramsDetail.add("historyId", model.getHistoryId());
		ProductCartDetailModel head = coreRepository.getData(sql.toString(), paramsDetail, ProductCartDetailModel.class);
		return head;
		}
	
	public String update(User00SaveModel model) {
		System.out.println("================================================="+model.getHistoryId());
		ModelMapper modelMapper = new ModelMapper();
//		product.setHistoryId(model.historyId);
		ProductCart myupdate = productcartRepository.findById(model.getHistoryId()).get();
		myupdate.setSweet(model.getSweet());
		myupdate.setComment(model.getComment());
		myupdate.setCount(model.getCount());
		modelMapper.map(model, myupdate);
		return String.valueOf(productcartRepository.saveAndFlush(myupdate).getHistoryId());
	}

}