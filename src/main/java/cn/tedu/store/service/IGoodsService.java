package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	
	/**
	 * 查找该分类下的所有商品
	 * @param categoryId 分类ID
	 * @param offset 偏移量（跳过多少条数据）
	 * @param count 获取的数据的最大数量
	 * @return 商品列表
	 */
	List<Goods> findGoodsByCategoryId(Integer categoryId,Integer offset,Integer count);
	
	/**
	 * 获取指定id的商品信息
	 * @param id 商品id
	 * @return 商品信息
	 */
	Goods getById(Integer id);
	
	
	/**
	 * 	获取优先级较高的N条数据
	 * @param count N条
	 * @return 得到的N条数据
	 */
	List<Goods> getByPriority(Integer count);
}
