package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;

import cn.tedu.store.service.IGoodsCategoryService;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService{
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	
	
	@Override
	public List<GoodsCategory> findGoodsByparentId(Integer parentId) {
		List<GoodsCategory> list = findByParentId(parentId);
		return list;
	}
	
	
	
	
	
	/**
	 * 查找该父级ID下的所有子集分类
	 * @return 商品信息
	 */
	private List<GoodsCategory> findByParentId(Integer parentId){
		return goodsCategoryMapper.findByParentId(parentId);
	}
}
