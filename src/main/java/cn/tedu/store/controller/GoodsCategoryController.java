package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;

import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/category")
public class GoodsCategoryController extends BaseController{
	
	@Autowired
	IGoodsCategoryService goodsCategoryService;
	
	@RequestMapping("/list/{parentId}")
	public ResponseResult<List<GoodsCategory>> getCategory(@PathVariable("parentId")Integer parentId){
		List<GoodsCategory> list = goodsCategoryService.findGoodsByparentId(parentId);
		return new ResponseResult<>(SUCCESS,list);
	}
}
