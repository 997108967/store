package cn.tedu.store.mapper;




import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.tedu.store.entity.District;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Test
	public void findByCode() {
		District district = districtMapper.findByCode("370126");
		System.out.println(district);
	}
	
	@Test
	public void findByParent() {
		List<District> district = districtMapper.findByParent("86");
		for(District dis:district) {
			System.out.println(dis);
		}
		System.out.println("end.");
	}
	
}
