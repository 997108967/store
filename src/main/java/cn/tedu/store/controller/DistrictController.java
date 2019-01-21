package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.District;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{
	
	@Autowired
	IDistrictService districtService;
	
	@RequestMapping("/list")
	public ResponseResult<List<District>> getListByParent(@RequestParam("parent")String parent){
		
		List<District> list = districtService.getByParent(parent);
		return new ResponseResult<>(SUCCESS,list);
	}
}
