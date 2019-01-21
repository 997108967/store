package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;
import cn.tedu.store.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	
	
	
	@Override
	public List<Goods> findGoodsByCategoryId(Integer categoryId,Integer offset,Integer count) {
		return findByCategoryId(categoryId, offset, count);
	}
	
	
	@Override
	public Goods getById(Integer id) {
		return findById(id);
	};
	
	
	@Override
	public List<Goods> getByPriority(Integer count) {
		return findByPriority(count);
	};
	
	
	
	/**
	 * 查找该分类下的所有商品
	 * @param categoryId 分类ID
	 * @param offset 偏移量（跳过多少条数据）
	 * @param count 获取的数据的最大数量
	 * @return 商品列表
	 */
	private List<Goods> findByCategoryId(Integer categoryId,Integer offset,Integer count){
		return goodsMapper.findByCategoryId(categoryId, offset, count);
	}
	
	/**
	 * 获取指定商品的信息
	 * @param id 商品ID
	 * @return 该商品信息
	 */
	private Goods findById(Integer id) {
		return goodsMapper.findById(id);
	}


	/**
	 * 	获取优先级较高的N条数据
	 * @param count N条
	 * @return 得到的N条数据
	 */
	private List<Goods> findByPriority(Integer count){
		return goodsMapper.findByPriority(count);
	}


	

	
}
