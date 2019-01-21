package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService{
	
	@Autowired
	DistrictMapper districtMapper;
	
	@Override
	public List<District> getByParent(String parent) {
		return findByParent(parent);
	}

	@Override
	public District getByCode(String code) {
		return findByCode(code);
	}

	
	/**
	 * 通过地区代号查询该地区的详细信息
	 * @param code 代号
	 * @return 查询到的地区信息 
	 */
	private District findByCode(String code) {
		return districtMapper.findByCode(code);
	};
	
	
	
	/**
	 * 根据父类编号查询子级的省/市/区的列表
	 * @param parent 父类编号
	 * @return 所属这个父类的字类地区信息
	 */
	private List<District> findByParent(String parent){
		return districtMapper.findByParent(parent);
	};
}
