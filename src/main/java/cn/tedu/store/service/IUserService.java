package cn.tedu.store.service;


import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.UserNotFoundException;
import cn.tedu.store.service.exception.UpdateException;

public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user 用户注册信息
	 * @return	成功注册的用户数据
	 * @throws DuplicateKeyException 
	 * @throws InsertException 
	 */
	User reg(User user) throws DuplicateKeyException,InsertException;
	
	/**
	 *  用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登陆后的用户数据 包含(id username,投降)
	 * @throws UserNotFoundException 用户名不存在   或（该用户为删除状态！！！）
	 * @throws PasswordNotMatchException 密码错误
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 *  修改密码
	 * @param password 新密码
	 * @param session
	 * @throws UpdateException 数据库修改异常
	 * @throws PasswordNotMatchException 密码不匹配异常
	 */
	void changePassword(String password,Integer id,String oldPassword) throws UpdateException, PasswordNotMatchException,UserNotFoundException;
	
	/**
	 * 	修改用户资料 (不含用户名，投降，密码)
	 * @param user 用户提交过来修改成为的用户信息
	 * @throws UpdateException 修改错误（数据库执行错误）异常
	 * @throws UserNotFoundException 用户找不到（或删除）异常
	 * @throws UpdateException 数据库执行异常
	 */
	void changeInfo(User user) throws UpdateException,UserNotFoundException;
	
	/**
	 * 获取正在登录的该用户信息
	 * @param id 用户ID
	 * @return	该用户信息
	 * @throws UserNotFoundException 该用户不存在异常
	 * @throws UpdateException 数据库执行异常
	 */
	User getInfoById(Integer id) throws UserNotFoundException;
	
	
	/**
	 *   修改头像
	 * @param id	用户ID
	 * @param avatar	头像地址
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer id,String avatar) throws UserNotFoundException,UpdateException;
}
