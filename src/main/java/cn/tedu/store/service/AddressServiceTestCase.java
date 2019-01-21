package cn.tedu.store.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {
	@Autowired
	private IAddressService addressService;
	
	@Test
	public void deleteAddress() {
		addressService.deleteAddress(28,8, "root");
	}
	
	
	
	@Test
	public void setDefault() {
		addressService.setDefault(19, 1, "r");
	}
	
	@Test
	public void findAddressByUid() {
		List<Address> list = addressService.getAddressByUid(1);
		for(Address address:list) {
			System.err.println(address);
		}
	}
	
	
	
	@Test
	public void create() {
		String username = "Admin";
		Address address = new Address();
		address.setUid(2);
		address.setName("同学");
		address.setProvince("370000");
		address.setCity("370100");
		address.setArea("370126");
		Address result = addressService.create(address, username);
		System.out.println(result);
	}
	
}
