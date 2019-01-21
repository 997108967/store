package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.CartNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;
import cn.tedu.store.vo.CartVO;
/**
 * 购物车业务层实现类
 * @author soft01
 *
 */
@Service
public class CartServiceimpl implements ICartService{

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void addToCart(Cart cart,String username) throws InsertException, UpdateException {
		//查询该商品在购物车中的信息
		Integer uid = cart.getUid();
		Integer goodsId = cart.getGid();
		Cart data = findByUidAndGid(uid, goodsId);
		Date date = new Date();
		
		cart.setCreatedUser(username);
		cart.setCreatedTime(date);
		cart.setModifiedTime(date);
		cart.setModifiedUser(username);
		
		//判断该商品是否存在
		if(data==null) {
			//不存在 添加该商品信息
			addnew(cart);
			return;
		}
		//该商品已存在 ，对相应的数量进行修改
		Integer id = data.getId();
		Integer count = cart.getCount();
		//对数量进行累加
		count = count + data.getCount();
		//更新该商品数量
		updateCount(id, count,username,new Date());
		
	}
	
	
	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	};
	
	@Override
	public void addCount(Integer id, Integer uid,String username) throws CartNotFoundException, AccessDeniedException, UpdateException {
		//根据商品ID查询数据
		Cart cart = findById(id);
		//判断是否为null
		if(cart==null) {
			//为null 抛出异常
			throw new CartNotFoundException("修改失败！该商品信息不存在！");
		}
		//判断数据归属是否不匹配
		if(cart.getUid()!=uid) {
			throw new AccessDeniedException("修改失败！商品信息与归属人不匹配！");
		}
		Integer count = cart.getCount();
		count++;
		updateCount(id, count, username, new Date());
	};
	
	
	@Override
	public List<CartVO> getByIds(Integer[] ids) {
		return findByIds(ids);
	};
	
	
	
	
	
	
	/**
	 * 查询该用户是否对该商品进行收藏
	 * @param uid 用户ID
	 * @param goodsId 商品ID
	 * @return 该商品信息，如果该用户不存在商品的收藏返回null
	 */
	private Cart findByUidAndGid(Integer uid,Integer goodsId) {
		return cartMapper.findByUidAndGid(uid, goodsId);
	};
	
	/**
	 * 对该商品进行添加
	 * @param cart 商品信息
	 */
	private void addnew(Cart cart) {
		Integer rows = cartMapper.addnew(cart);
		if(rows!=1) {
			throw new InsertException("添加购物车失败！出现未知错误！");
		}
	};
	
	
	/**
	 * 	更新商品的收藏数量
	 * @param id 商品ID
	 * @param count 商品的收藏数量
	 */
	private void updateCount(Integer id,Integer count,String modifiedUser,Date modifiedTime) {
		Integer rows = cartMapper.updateCount(id, count,modifiedUser,modifiedTime);
		if(rows!=1) {
			throw new UpdateException("修改购物车商品数量失败！出现未知错误！");
		}
	};
	
	
	/**
	 * 	查询该用户的购物车商品信息
	 * @param uid 用户iid
	 * @return 商品信息
	 */
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}
	
	/**
	 * 根据商品ID获取购物车数据
	 * @param id 购物车数据的ID
	 * @return 匹配的购物车数据，如果数据不存在，则返回null
	 */
	private Cart findById(Integer id) {
		return cartMapper.findById(id);
	}
	
	/**
	 * 通过多个id获取对应商品数据的集合
	 * @param ids 多个ID
	 * @return 商品数据的集合
	 */
	private List<CartVO> findByIds(Integer[] ids){
		return cartMapper.findByIds(ids);
	}


	
}
