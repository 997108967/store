package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;

public interface IAddressService {
	
	/**
	 * 创建新的收货地址
	 * @throws InsertException
	 * @throws username 用户名
	 */
	Address create(Address address,String username) throws InsertException;
	
	
	/**
	 * 获取当前用户的收货地址
	 * @return 当前用户的收货地址
	 */
	List<Address> getAddressByUid(Integer id);
	
	/**
	 *  设置默认收货地址
	 * @param id	要修改的收获地址ID
	 * @param uid		用户ID
	 * @param username 用户名
	 * @throws UpdateException 数据库出现未知异常
	 */
	void setDefault(Integer id,Integer uid,String username)throws UpdateException;
	
	/**
	 * 删除指定的收货地址
	 * @param id 收获地址ID
	 * @param uid		用户ID
	 * @param username  用户名
	 * @throws DeleteException 删除异常
	 */
	void deleteAddress(Integer id,Integer uid,String username)throws DeleteException;
}
