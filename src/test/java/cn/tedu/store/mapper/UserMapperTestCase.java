package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void updateAvatar() {
		Integer rows = userMapper.updateAvatar(1, "update", "情", new Date());
		System.out.println(rows);
	}
	
	@Test
	public void updateInfo() {
		Date date = new Date();
		User user = new User();
		user.setPhone("13969185853");
		user.setId(2);
		user.setEmail("156185445@qq.com");
		user.setGender(0);
		user.setModifiedUser("springboot");
		user.setModifiedTime(date);
		Integer row =  userMapper.updateInfo(user);
		System.err.println(row);
	}
	
	
	@Test
	public void updatePassword() {
		Date date = new Date();
		Integer row =  userMapper.updatePassword(7, "1", "my",date );
		System.err.println(row);
	}
	
	
	@Test
	public void findById() {
		User user = userMapper.findById(1);
		System.err.println(user);
	}
	
	
	
	
	
	
	
	@Test
	public void addnew() {
		Date now = new Date();
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		user.setGender(1);
		user.setPhone("13658615163");
		user.setEmail("997108967@qq.com");
		user.setSalt("Hello,MD5");
		user.setCreatedUser("Admin");
		user.setModifiedUser("Admin");
		user.setModifiedTime(now);
		user.setCreatedTime(now);
		Integer rows = userMapper.addnew(user);
		System.err.println(rows);
	}
	
	@Test
	public void findByUsername() {
		User user = userMapper.findByUsername("root");
		System.err.println(user);
	}
}
