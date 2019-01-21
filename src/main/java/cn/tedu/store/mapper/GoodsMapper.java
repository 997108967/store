package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

/**
 * 商品数据的持久层
 * @author soft01
 *
 */
public interface GoodsMapper {
	
	
	/**
	 * 查找该分类下的所有商品
	 * @param categoryId 分类ID
	 * @param offset 偏移量（跳过多少条数据）
	 * @param count 获取的数据的最大数量
	 * @return 商品列表
	 */
	List<Goods> findByCategoryId(@Param("categoryId")Integer categoryId,@Param("offset")Integer offset,@Param("count")Integer count);
	
	/**
	 * 获取指定商品的信息
	 * @param id 商品ID
	 * @return 该商品信息 如果没有匹配的数据则返回null
	 */
	Goods findById(Integer id);
	
	/**
	 * 	获取优先级较高的N条数据
	 * @param count N条
	 * @return 得到的N条数据
	 */
	List<Goods> findByPriority(Integer count);
	
	
}
