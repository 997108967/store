package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

/**
 * 
 * @author soft01
 *
 */
public interface IDistrictService {
	
	/**
	 * 根据父类编号查询子级的省/市/区的列表
	 * @param parent 父类编号
	 * @return 所属这个父类的字类地区信息
	 */
	List<District> getByParent(String parent);
	
	
	/**
	 * 通过地区代号查询该地区的详细信息
	 * @param code 代号
	 * @return 查询到的地区信息 
	 */
	District getByCode(String code);
}
