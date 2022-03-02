package com.pj.ad.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.pj.ad.service.Admin00DetailModel.GetDetail;
import com.pj.ad.repository.core.PageModel;
import com.pj.ad.utils.CoreUtils;

//@Transactional
@Service
public class AddressService {
	
	@Autowired
	private CoreRepository coreRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	
	
	public String save(Address00SaveModel model)throws Exception {
		ModelMapper modelMapper = new ModelMapper();
//		Date date = new Date();
		Address address = new Address();
		address.setName(model.getName());
		address.setLastName(model.getLastName());
		address.setNumber(model.getPhone());
		String addressSave = String.valueOf(addressRepository.saveAndFlush(address).getCustomerId());
		return addressSave;
	}
	
	public GridData search(AddressSearchModel model) {
		SqlParams params = SqlParams.create(model);
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.* FROM (  ");
		sql.append(" SELECT row_number() OVER (ORDER BY customerId) AS \"rowNum\" ");
		sql.append(" , name AS \"name\" ");
		sql.append(" , lastName AS \"lastName\" ");
		sql.append(" , number  AS \"number\" ");
		sql.append(" WHERE 1=1 ");

		sql.append(" ) a ");
		sql.append(model.generateSqlOrderBy("a",
				Sort.by(model.getSorts().get(0).getColId(), Direction.fromString(model.getSorts().get(0).getSort()))));

		return coreRepository.searchPagingGridData(sql.toString(), params);
	}
	
	
//	public Product getDetail(GetDetail model) {
//		Product data = productRepository.findById(model.productId).get();
//		return data;
//	}
	
//	public Admin00DetailModel getDetail(Admin00DetailModel model) {
//		
//		Product data = productRepository.findById(model.productId).get();
//		System.out.println(model);
//		Admin00DetailModel detail = new Admin00DetailModel();
//		detail.setName(data.getProductName());
//		detail.setPrice(data.getPrice());
//		detail.setType(data.getProductTypeName());
//		return detail;
//	}
//	
//	public String update(Admin00SaveModel model) {
//		ModelMapper modelMapper = new ModelMapper();
//		Product product = new Product();
//		product.setProductId(model.productId);
//		Product myupdate = productRepository.findById(model.getProductId()).get();
//		myupdate.setProductName(model.getName());
//		myupdate.setProductTypeName(model.getType());
////		myupdate.setFile(model.getFile());
//		modelMapper.map(model, myupdate);
//		return String.valueOf(productRepository.saveAndFlush(myupdate).getProductId());
//	}
//	
}