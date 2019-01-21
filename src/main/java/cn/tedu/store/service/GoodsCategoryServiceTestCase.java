package cn.tedu.store.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCategoryServiceTestCase {
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	
	@Test
	public void findGoodsByparentId() {
		List<GoodsCategory> list = goodsCategoryMapper.findByParentId(1000);
		for (GoodsCategory goodsCategory : list) {
			System.out.println(goodsCategory );
		}
	}
	
}
