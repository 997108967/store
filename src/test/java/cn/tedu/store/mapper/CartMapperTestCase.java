package cn.tedu.store.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.vo.CartVO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTestCase {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Test
	public void findByids() {
		Integer[] ids = {11,12,13};
		List<CartVO> list = cartMapper.findByIds(ids);
		for (CartVO cartVO : list) {
			System.out.println(cartVO);
		}
	}
	@Test
	public void findByid() {
		System.out.println(cartMapper.findById(10));
	}

	@Test
	public void findByUid() {
		List<CartVO> list = cartMapper.findByUid(1);
		for (CartVO cartVO : list) {
			System.out.println(cartVO);
		}
	}


	@Test
	public void addnew() {
		Cart cart = new Cart();
		cart.setGid(1);
		cart.setUid(2);
		cart.setPrice(1000);
		cart.setCount(1);
	Integer rows = 	cartMapper.addnew(cart);
	}
	
	@Test
	public void findByUidAndGid() {
		Cart acrts = cartMapper.findByUidAndGid(0, 0);
		System.out.println(acrts);
	}
	
}
