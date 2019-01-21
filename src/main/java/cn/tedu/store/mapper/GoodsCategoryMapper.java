package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

/**
 * 商品分类数据的持久层接口
 * @author soft01
 *
 */
public interface GoodsCategoryMapper {
	
	/**
	 * 查找该父级分类下的所有子集分类
	 * @param parentId 父级分类的ID
	 * @return 子集分类的数据
	 */
	List<GoodsCategory> findByParentId(Integer parentId);
}
