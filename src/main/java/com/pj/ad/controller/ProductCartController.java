package com.pj.ad.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.ad.model.User00SaveModel;
import com.pj.ad.service.ProductCartDetailModel;
import com.pj.ad.service.ProductCartDetailModel.GetDetail;
import com.pj.ad.service.ProductCartSearchModel;
import com.pj.ad.service.ProductCartService;
import com.pj.ad.utils.Response;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productcart")
public class ProductCartController{
	
	@Autowired
	private ProductCartService productcartService; 
	
	@PostMapping("/search")
	public Response search(@RequestBody ProductCartSearchModel model) throws Exception{
		return Response.success(productcartService.search(model));
	}
	
//	@PostMapping("/cancel")
//	public void cancel(@RequestBody GetDetail model) throws Exception {
//		productcartService.cancel(model);
//	}
	
	@PostMapping("/getDetail")
	public ProductCartDetailModel getDetail(@RequestBody ProductCartDetailModel model) throws Exception {
		return productcartService.getDetail(model);
	}
	
	@PostMapping("/update")
	public Response update(@RequestBody User00SaveModel model) throws Exception {
		return Response.success(productcartService.update(model));
	}
	
	
	
}
