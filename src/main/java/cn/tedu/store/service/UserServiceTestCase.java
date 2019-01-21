package cn.tedu.store.service;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	private IUserService userService;
	
	
	@Test
	public void changeAvatar() {
	
		try {
			userService.changeAvatar(2,"46543214");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	
	}
	
	@Test
	public void changeInfo() {
		User user = new User();
		user.setId(2);
		user.setPhone("1");
		user.setEmail("1@qq.com");
		user.setGender(1);
		try {
			userService.changeInfo(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
		
	}
	
	
	@Test
	public void login() {
		String username = "root";
		String password = "1234";
		
		try {
			User user = userService.login(username, password);
			System.out.println(user);
		} catch (Exception e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	
	
	
	@Test
	public void reg() {
		User user = new User();
		user.setUsername("springBoot");
		user.setPassword("5678");
		user.setGender(1);
		user.setPhone("13668813729");
		user.setEmail("1780248979@qq.com");
		try {
			User data = userService.reg(user);
			System.err.println(data);
		} catch (ServiceException e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	}
	
}
