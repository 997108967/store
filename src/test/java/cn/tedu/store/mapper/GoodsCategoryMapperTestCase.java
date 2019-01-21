package cn.tedu.store.mapper;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsCategoryMapperTestCase {
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	
	@Test
	public void findByCategroyId() {
		List<GoodsCategory> list = goodsCategoryMapper.findByParentId(1000);
		for (GoodsCategory goodsCategory : list) {
			System.out.println(goodsCategory);
		}
	}

	
}
