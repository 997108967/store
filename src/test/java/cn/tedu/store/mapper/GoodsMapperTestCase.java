package cn.tedu.store.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Goods;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTestCase {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	
	@Test
	public void findByPriority() {
		List<Goods> list = goodsMapper.findByPriority(4);
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}
	
	@Test
	public void findById() {
		Goods goods = goodsMapper.findById(10000001);
		System.out.println(goods);
	}
	
	@Test
	public void findByCategroyId() {
		List<Goods> list = goodsMapper.findByCategoryId(238,1,10);
		for (Goods goods : list) {
			System.out.println(goods);
		}
	}

	
}
