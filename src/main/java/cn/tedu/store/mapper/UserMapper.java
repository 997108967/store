package cn.tedu.store.mapper;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

/**
 * 处理用户数据的持久层
 * @author soft01
 *
 */
public interface UserMapper {
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 根据用户ID查找该用户
	 * @param id 用户ID
	 * @return 返回查找到的该用户
	 */
	User findById(Integer id);
	
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer addnew(User user);
	
	/**
	 * 修改密码
	 * @param id 用户ID
	 * @param password 新密码
	 * @param oldpassword 旧密码
	 * @param modifiedUser 最后修改人
	 * @param modifiedTime 最后修改时间
	 * @return 受影响的行数
	 */
	Integer updatePassword(@Param("id") Integer id,@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 		修改用户数据（不包含用户名，投降，密码）
	 * @param user	要修改的用户数据
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);
	
	/**
	 * 修改用户头像（地址信息）
	 * @param id	用户ID
	 * @param avatar 头像储存地址	
	 * @param modifiedUser	最终修改人
	 * @param modifiedTime	最终修改时间
	 * @return 受影响的行数
	 */
	Integer updateAvatar(@Param("id")Integer id,@Param("avatar")String avatar,
			@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
