package com.pj.ad.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.ad.service.Address00SaveModel;
import com.pj.ad.service.AddressService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/address")
public class AddressController{
	
	@Autowired
	private AddressService addressService; 
	
	@PostMapping("/save")
	public String save(@RequestBody Address00SaveModel model) throws Exception {
//		System.out.println(model);
		return addressService.save(model);
	}
	
//	@PostMapping("/getDetail")
//	public Admin00DetailModel getDetail(@RequestBody Admin00DetailModel model) throws Exception {
//		return admin00Service.getDetail(model);
//	}
//	
//	
//	@PostMapping("/update")
//	public Response update(@RequestBody Admin00SaveModel model) throws Exception {
//		return Response.success(admin00Service.update(model));
//	}
	
	
	
}
