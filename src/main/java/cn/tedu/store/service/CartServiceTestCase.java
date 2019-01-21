package cn.tedu.store.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.exception.ServiceException;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {
	@Autowired
	private ICartService service;
	
	@Test
	public void addCount() {
		System.err.println("start");
		try {
			service.addCount(0, 1, "root");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
		System.err.println("end..");
	}
	
	
	
	
	@Test
	public void addToCart() {
		Cart cart = new Cart();
		cart.setUid(2);
		cart.setGid(3);
		cart.setPrice(3000);
		cart.setCount(10);
		String username = "情";
		System.out.println("begin..");
		service.addToCart(cart,username);
		System.out.println("end..");
	}
	
}
