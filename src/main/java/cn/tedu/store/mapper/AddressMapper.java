package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址的持久层
 * @author soft01
 *
 */
public interface AddressMapper {
	
	/**
	 * 添加收货地址
	 * @param address
	 * @return
	 */
	Integer addnew(Address address);
	
	/**
	 * 根据用户ID查询该用户是否存在收货地址
	 * @param uid
	 * @return
	 */
	Integer findCountByUid(Integer uid);
	
	/**
	 * 查询该用户收货地址
	 * @param id 用户ID
	 * @return 该用户的收获地址
	 * 
	 */
	List<Address> findAddressByUid(Integer id);
	
	
	/**
	 * 将指定用户的默认地址全部设置为0(全都不默认)
	 */
	Integer updateNonDefault(Integer uid);
	
	/**
	 * 将指定地址设置未默认地址
	 */
	Integer updateDefault(@Param("id")Integer id,@Param("username")String username,@Param("date")Date date);
	/**
	 * 根据ID查询收获地址
	 * @param id 收货地址的ID
	 * @return 匹配的收货地址 若果没有匹配的数据则返回null
	 */
	Address findById(Integer id);
	
	/**
	 * 删除指定的收货地址（根据ID）
	 * @return
	 */
	Integer deleteById(Integer id);
	
	/**
	 * 查询最后修改的收获地址
	 * @param uid
	 * @return
	 */
	Address findLastModified(Integer uid);
}
