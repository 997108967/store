package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface IGoodsCategoryService {
	
	/**
	 * 查询该父级ID下的子级分类信息
	 * @param parentId 父级id
	 * @return 商品分类信息
	 */
	List<GoodsCategory> findGoodsByparentId(Integer parentId);
	
}
