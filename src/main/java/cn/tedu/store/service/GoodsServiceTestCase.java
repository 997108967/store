package cn.tedu.store.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTestCase {
	@Autowired
	private IGoodsService goodsService;
	
	
	@Test
	public void findByPriority() {
		List<Goods> list = goodsService.getByPriority(4);
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}
	
	
	@Test
	public void findById() {
		Goods goods = goodsService.getById(10000002);
		System.out.println(goods);
	}
	
	
	@Test
	public void findGoodsByCategoryId() {
		List<Goods> list = goodsService.findGoodsByCategoryId(163,1,2);
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}
	
}
