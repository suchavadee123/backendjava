package com.pj.ad.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.ad.entity.Product;
import com.pj.ad.model.Admin00SaveModel;
import com.pj.ad.model.Admin00SearchModel;
import com.pj.ad.model.User00SaveModel;
import com.pj.ad.repository.core.GridData;
import com.pj.ad.service.Admin00DetailModel.GetDetail;
import com.pj.ad.service.Admin00DetailModel;
import com.pj.ad.service.Admin00Service;
import com.pj.ad.utils.Response;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin00")
public class Admin00Controller{
	
	@Autowired
	private Admin00Service admin00Service; 
	
	@PostMapping("/save")
	public String save(@RequestBody Admin00SaveModel model) throws Exception {
//		System.out.println(model);
		return admin00Service.save(model);
	}
	
	@PostMapping("/saveuser")
	public String saveuser(@RequestBody User00SaveModel model) throws Exception {
		System.out.println("+++++++++++++++++++++++++++++++++++"+model);
		return admin00Service.saveuser(model);
	}
	
	@PostMapping("/search")
	public Response search(@RequestBody Admin00SearchModel model) throws Exception{
		return Response.success(admin00Service.search(model));
	}
	
//	@PostMapping("/search")
//	public GridData search(@RequestBody Admin00SearchModel model) {
//		System.out.println("+++++++++++++++++++"+admin00Service.search(model));
//		return admin00Service.search(model);
//	}
	
	@PostMapping("/getDetail")
	public Admin00DetailModel getDetail(@RequestBody Admin00DetailModel model) throws Exception {
		return admin00Service.getDetail(model);
	}
	
//	@PostMapping("/getDetailHistory")
//	public Admin00DetailModel getDetailHistory(@RequestBody Admin00DetailModel model) throws Exception {
//		return admin00Service.getDetailHistory(model);
//	}
	
	@PostMapping("/cancel")
	public void cancel(@RequestBody GetDetail model) throws Exception {
		admin00Service.cancel(model);
	}
	
	@PostMapping("/update")
	public Response update(@RequestBody Admin00SaveModel model) throws Exception {
		return Response.success(admin00Service.update(model));
	}
	
	
	
}
