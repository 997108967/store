package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.CartNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;
import cn.tedu.store.vo.CartVO;

public interface ICartService {
	
	/**
	 * 添加商品到购物车
	 * @param cart 商品信息
	 * @param username 当前操作的执行人
	 * @throws InsertException
	 * @throws UpdateException
	 */
	void addToCart(Cart cart,String username) throws InsertException,UpdateException;
	
	
	/**
	 * 	获取该用户的购物车商品信息
	 * @param uid 用户iid
	 * @return 商品信息
	 */
	List<CartVO> getByUid(Integer uid);
	
	
	/**
	 * 通过多个id获取对应商品数据的集合
	 * @param ids 多个ID
	 * @return 商品数据的集合
	 */
	List<CartVO> getByIds(Integer[] ids);
	
	
	
	/**
	 * 添加购物车中商品数量
	 * @param id 商品ID
	 * @param uid 用户ID
	 * @param username 用户名
	 * @throws CartNotFoundException 
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void addCount(Integer id,Integer uid,String username)throws CartNotFoundException,AccessDeniedException,UpdateException;
}
