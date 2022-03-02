package com.pj.ad.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.ad.entity.Product;
import com.pj.ad.entity.ProductCart;
import com.pj.ad.model.Admin00SaveModel;
import com.pj.ad.model.Admin00SearchModel;
import com.pj.ad.model.User00SaveModel;
import com.pj.ad.repository.ProductCartRepository;
import com.pj.ad.repository.ProductRepository;
import com.pj.ad.repository.core.CoreRepository;
import com.pj.ad.repository.core.GridData;
import com.pj.ad.repository.core.Sort;
import com.pj.ad.repository.core.SqlParams;
import com.pj.ad.service.Admin00DetailModel.GetDetail;
import com.pj.ad.repository.core.PageModel;
import com.pj.ad.utils.CoreUtils;

//@Transactional
@Service
public class Admin00Service {
	
	@Autowired
	private CoreRepository coreRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCartRepository productcartRepository;

	
	
	public String save(Admin00SaveModel model)throws Exception {
		ModelMapper modelMapper = new ModelMapper();
//		Date date = new Date();
		Product product = new Product();
		product.setProductName(model.getName());
		product.setProductTypeName(model.getType());
		product.setPrice(model.getPrice());
//		product.setFile(model.getFile());
		String productSave = String.valueOf(productRepository.saveAndFlush(product).getProductId());
		return productSave;
	}
	
	public String saveuser(User00SaveModel model)throws Exception {
		System.out.println("================================================================================"+model);
		ModelMapper modelMapper = new ModelMapper();
		ProductCart productcart = new ProductCart();
		productcart.setProductId(model.getProductId());;
		productcart.setSweet(model.getSweet());
		productcart.setComment(model.getComment());
		productcart.setCount(model.getCount());
		String productSaveuser = String.valueOf(productcartRepository.saveAndFlush(productcart).getHistoryId());
		return productSaveuser;
	}
	
	public GridData search(Admin00SearchModel model) {
		SqlParams params = SqlParams.create(model);
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.* FROM (  ");
		sql.append(" SELECT row_number() OVER (ORDER BY product_id) AS \"rowNum\" ");
		sql.append(" , product_id AS \"productId\" ");
		sql.append(" , product_name AS \"productName\" ");
		sql.append(" , price AS \"price\" ");
		sql.append(" , product_type_name AS \"type\" ");
		sql.append(" , file AS \"file\" ");
		sql.append(" FROM product ");
		sql.append(" WHERE 1=1 ");
		

		if (CoreUtils.isNotNull(model.getType())) {
			sql.append(" AND product_type_name = :type ");
			params.add("type", model.getType());
		}
		
		sql.append(" ) a ");
		sql.append(model.generateSqlOrderBy("a",
				Sort.by(model.getSorts().get(0).getColId(), Direction.fromString(model.getSorts().get(0).getSort()))));

		return coreRepository.searchPagingGridData(sql.toString(), params);
	}
	
//	public Product getDetail(GetDetail model) {
//		Product data = productRepository.findById(model.productId).get();
//		return data;
//	}
	
	public Admin00DetailModel getDetail(Admin00DetailModel model) {
		Product data = productRepository.findById(model.productId).get();
		System.out.println(model);
		Admin00DetailModel detail = new Admin00DetailModel();
		detail.setName(data.getProductName());
		detail.setPrice(data.getPrice());
		detail.setType(data.getProductTypeName());
		return detail;
	}
	
//	public Admin00DetailModel getDetailHistory(Admin00DetailModel model) {
//		Product data = productRepository.findById(model.productId).get();
//		ProductCart History = productcartRepository.findById(model.historyId).get();
//		Admin00DetailModel detail = new Admin00DetailModel();
//		detail.setName(data.getProductName());
//		detail.setPrice(data.getPrice());
//		detail.setCount(History.getCout());
//		detail.setComment(History.getComment());
//		detail.setSweet(History.getSweet());
//		System.out.println("======================================================================="+detail);
//		return detail;
//	}
	
	public String update(Admin00SaveModel model) {
		ModelMapper modelMapper = new ModelMapper();
		Product product = new Product();
		product.setProductId(model.productId);
		Product myupdate = productRepository.findById(model.getProductId()).get();
		myupdate.setProductName(model.getName());
		myupdate.setProductTypeName(model.getType());
//		myupdate.setFile(model.getFile());
		modelMapper.map(model, myupdate);
		return String.valueOf(productRepository.saveAndFlush(myupdate).getProductId());
	}
	
	public void cancel(GetDetail model) throws Exception{
		productRepository.deleteById(model.productId);
	}
		
}