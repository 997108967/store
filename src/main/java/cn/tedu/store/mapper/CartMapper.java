package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

/**
 * 购物车数据的持久层接口
 * @author soft01
 *
 */
public interface CartMapper {
	
	/**
	 * 查询该用户是否对该商品进行收藏
	 * @param uid 用户ID
	 * @param goodsId 商品ID
	 * @return 该商品信息，如果该用户不存在商品的收藏返回null
	 */
	Cart findByUidAndGid(@Param("uid") Integer uid,@Param("goodsId") Integer goodsId);
	
	/**
	 * 对该商品进行收藏
	 * @param cart 收藏信息
	 * @return 执行成功返回的生效行数
	 */
	Integer addnew(Cart cart);
	
	/**
	 * 	更新商品的收藏数量
	 * @param id 商品ID
	 * @param count 商品的收藏数量
	 * @return 执行成功返回的生效行数	
	 */
	Integer updateCount(@Param("id") Integer id, @Param("count") Integer count,
			@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 	查询该用户的购物车商品信息
	 * @param uid 用户iid
	 * @return 商品信息
	 */
	List<CartVO> findByUid(Integer uid);
	
	/**
	 * 通过多个id获取对应商品数据的集合
	 * @param ids 多个ID
	 * @return 商品数据的集合
	 */
	List<CartVO> findByIds(Integer[] ids);
	
	
	
	
	/**
	 * 根据商品ID获取购物车数据
	 * @param id 购物车数据的ID
	 * @return 匹配的购物车数据，如果数据不存在，则返回null
	 */
	Cart findById(Integer id);
	
}
