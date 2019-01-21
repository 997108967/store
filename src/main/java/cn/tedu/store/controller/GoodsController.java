package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController{
	
	@Autowired
	IGoodsService goodsService;
	
	@RequestMapping("/list/{categoryId}")
	public ResponseResult<List<Goods>> getListGoods(@PathVariable("categoryId")Integer categoryId){
		List<Goods> list = goodsService.findGoodsByCategoryId(categoryId, 0, 20);
		return new ResponseResult<>(SUCCESS,list);
	}
	
	@RequestMapping("/details/{id}")
	public ResponseResult<Goods> getGoods(@PathVariable("id")Integer id){
		Goods goods = goodsService.getById(id);
		return new ResponseResult<>(SUCCESS,goods);
	}
	
	@RequestMapping("/hot")
	public ResponseResult<List<Goods>> getHotGoods(){
		List<Goods> list = goodsService.getByPriority(4);
		return new ResponseResult<List<Goods>>(SUCCESS,list);
	}
}
