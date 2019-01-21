package cn.tedu.store.mapper;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {
	
	@Autowired
	private AddressMapper addressMapper;
	
	
	@Test
	public void deleteAddress() {
		addressMapper.deleteById(2);
	}
	
	@Test
	public void findLastModified() {
		Address address = addressMapper.findLastModified(1);
		System.out.println(address);
	}
	
	
	@Test
	public void updateDefault() {
		Integer row = addressMapper.updateDefault(2, "root", new Date());
		System.out.println(row);
	}
	
	@Test
	public void updateNonDefault() {
		Integer row = addressMapper.updateNonDefault(2);
		System.out.println(row);
	}
	
	
	@Test
	public void findAddressByUid() {
		List<Address> list = addressMapper.findAddressByUid(1);
		for(Address address:list) {
			System.err.println(address);
		}
	}
	
	@Test
	public void insertAddress() {
		Address address = new Address();
		address.setUid(3);
		address.setName("情");
		address.setProvince("110000");
		address.setCity("110001");
		address.setArea("110002");
		address.setDistrict("山东省济南市天桥区");
		address.setAddress("三联大厦");
		address.setPhone("13658615163");
		address.setTel("0531-88881234");
		address.setTag("公司");
		address.setZip("251600");
		address.setIsDefault(1);
		address.setCreatedTime(new Date());
		address.setCreatedUser("情");
		address.setModifiedUser("情");
		address.setModifiedTime(new Date());
		Integer rows = addressMapper.addnew(address);
		System.err.println(rows);
	}
	
	@Test
	public void findCountByUid() {
		Integer count = addressMapper.findCountByUid(1);
		System.err.println(count);
	}
	
	
}
