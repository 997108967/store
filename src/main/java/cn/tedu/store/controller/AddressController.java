package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;

import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Autowired
	IAddressService addressService;
	
	@PostMapping("/create")
	public ResponseResult<Void> handleCreate(Address address,HttpSession session){
		//根据session获取username
		String username = session.getAttribute("username").toString();
		//根据session获取uid
		Integer uid = getIdFromSession(session);
		//将uid封装到address中
		address.setUid(uid);
		//调用业务层对象执行创建收货地址
		Address add = addressService.create(address, username);
		System.out.println(add);
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("/list")
	public ResponseResult<List<Address>> getAddressList(HttpSession session){
		Integer id = getIdFromSession(session);
		List<Address> list = addressService.getAddressByUid(id);
		return new ResponseResult<List<Address>>(SUCCESS,list);
	}
	
	@GetMapping("/default/{id}")
	public ResponseResult<Void> setDefault(HttpSession session,@PathVariable("id") Integer id){
		Integer uid = getIdFromSession(session);
		String username = session.getAttribute("username").toString();
		addressService.setDefault(id, uid, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseResult<Void> deleteAddress(HttpSession session,@PathVariable("id") Integer id){
		Integer uid = getIdFromSession(session);
		String username = session.getAttribute("username").toString();
		addressService.deleteAddress(id, uid, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
}
