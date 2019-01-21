package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.UserNotFoundException;
import cn.tedu.store.service.exception.UpdateException;

@Service
public class UserServiceImpl implements IUserService{ 

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		//根据尝试注册的用户名查询用户数据
		User data = findByUsername(user.getUsername());
		//判断查询到的数据是否为null
		if(data==null) {
			//是：用户名不存在，允许注册，则处理密码加密
			
			//【补充非用户提交的数据】
			//4项日志
			Date now = new Date();
			user.setCreatedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			
			//加密1：获取随机的UUID作为盐值
			String salt = UUID.randomUUID().toString();
			//加密2：获取用户提交的密码
			String srcPassword = user.getPassword();
			//加密3：基于原始密码和盐值执行加密，获取通过MD5进行加密的密码
			String md5Password = getMd5Password(srcPassword, salt);
			//加密4：将加密后的密码封装在user对象中
			user.setPassword(md5Password);
			//加密5：将盐值封装在user对象中
			user.setSalt(salt);
			//执行注册
			addnew(user);
			//返回注册的用户对象
			return user;
		}else {
			//否：用户名已被占用，抛出DuplicateKeyException 异常
			throw new DuplicateKeyException("注册失败！常是注册的用户名("+user.getUsername()+")被占用！");
		}
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User user = findByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("登录失败！您尝试登录的用户名:"+username+"不存在！");
		}
		String salt = user.getSalt();
		String md5Password = getMd5Password(password, salt);
		if(md5Password.equals(user.getPassword())) {
			if(0==user.getIsDelete()) {
				user.setSalt(null);
				user.setPassword(null);
				return user;
			}else {
				throw new UserNotFoundException("登录失败！您尝试登录的用户名:"+username+"已经注销!！");
			}
		}else {
			throw new PasswordNotMatchException("登录失败！密码错误！");
		}
	};
	
	
	@Override
	public void changePassword(String password,Integer id,String oldPassword) throws UpdateException,PasswordNotMatchException,UserNotFoundException{
		//获取对应的用户
		User user = findById(id);
		//判断该用户是否存在
		if(user==null) {
			//不存在 抛出不存在异常 
			throw new UserNotFoundException("修改失败！该用户不存在！"); 
		}
		//判断该用户是否被注销
		if(user.getIsDelete()==1) {
			//是 抛出 该用户被冻结 异常
			throw new UserNotFoundException("修改失败！该用户被冻结！"); 
		}
		//获取加密后的密码
		oldPassword = getMd5Password(oldPassword, user.getSalt());
		//判断 密码是否正确
		if(!user.getPassword().equals(oldPassword)) {
			//不正确 抛出密码不匹配异常
			throw new PasswordNotMatchException("修改失败！您输入的原密码不正确！");
		}
		//密码正确，可以修改密码
		//对新密码进行加密
		password = getMd5Password(password, user.getSalt());
		Date now = new Date();
		//修改密码
		updatePassword(id, password, user.getUsername(), now);
	}
	
	@Override
	public void changeInfo(User user) throws UpdateException, UserNotFoundException {
		//根据user.getId() id查询用户数据
		User data = findById(user.getId());
		//判断数据是否为null
		if(data==null) {
			//是 抛出用户不存在异常
			throw new UserNotFoundException("修改资料失败！该用户不存在！");
		}
		//否
		//判断isDelete是否为1
		if(data.getIsDelete()==1) {
			//是 抛出用户不存在异常
			throw new UserNotFoundException("修改资料失败！该用户已被删除！");
		}
		//否
		//向参数对象中封装modifiedUser,modifiedTime
		user.setModifiedTime(new Date());
		//执行修改
		user.setModifiedUser(data.getUsername());
		updateInfo(user);
	};
	
	@Override
	public User getInfoById(Integer id) throws UserNotFoundException{
		//获取该用户信息
		User user = findById(id);
		if(user==null) {
			throw new UserNotFoundException("查询资料失败！该用户已被删除！");
		}
		if(user.getIsDelete()==1) {
			throw new UserNotFoundException("查询资料失败！该用户已被删除！");
		}
		//声明一个user对象 返回给客户端
		User data = new User();
		data.setUsername(user.getUsername());
		data.setEmail(user.getEmail());
		data.setPhone(user.getPhone());
		data.setGender(user.getGender());
		return data;
	}
	
	
	@Override
	public void changeAvatar(Integer id, String avatar) throws UserNotFoundException, UpdateException {
		User user = findById(id);
		if(user==null) {
			throw new UserNotFoundException("修改头像失败,该用户不存在！");
		}
		if(user.getIsDelete()==1) {
			throw new UserNotFoundException("修改头像失败,该用户被删除！");
		}
		String modifiedUser = user.getUsername();
		Date modifiedTime = new Date();
		updateAvatar(id, avatar, modifiedUser, modifiedTime);
	}
	
	
	
	
	
	/**
	 *   对密码进行加密
	 * @param srcPassword 用户密码
	 * @param salt 盐值
	 * @return 加密后的密码
	 */
	private String getMd5Password(String srcPassword,String salt) {
		//盐值	拼接	原密码	拼接	盐值
		String str = salt + srcPassword + salt;
		//循环执行10次摘要运算
		for(int i=0;i<10;i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		//返回摘要结果
		return str;
	}
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	};
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 */
	private void addnew(User user) {
		Integer rows = userMapper.addnew(user);
		if(rows!=1) {
			throw new InsertException("增加用户数据时出现未知错误！");
		}
	}

	/**
	 * 修改密码
	 * @param id 用户ID
	 * @param password 新密码
	 * @param modifiedUser 最后修改人
	 * @param modifiedTime 最后修改时间
	 */
	private void updatePassword(Integer id,String password,String modifiedUser,Date modifiedTime) throws UpdateException{
		Integer rows = userMapper.updatePassword(id, password, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("修改密码失败！出现未知错误！");
		}
	};
	
	
	/**
	 * 修改用户资料
	 * @param user 要修改的用户信息
	 */
	private void updateInfo(User user) {
		Integer rows = userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("修改用户信息失败！出现未知错误！");
		}
	}
	
	/**
	 * 修改用户头像（地址信息）
	 * @param id	用户ID
	 * @param avatar 头像储存地址	
	 * @param modifiedUser	最终修改人
	 * @param modifiedTime	最终修改时间
	 * @return 受影响的行数
	 */
	void updateAvatar(Integer id,String avatar,String modifiedUser,Date modifiedTime) {
		Integer rows = userMapper.updateAvatar(id, avatar, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("修改头像失败！出现未知错误！");
		}
	};
	/**
	 * 根据用户ID查找该用户
	 * @param id 用户ID
	 * @return 返回查找到的该用户
	 */
	private User findById(Integer id) {
		return userMapper.findById(id);
	}

}
