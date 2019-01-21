package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.DeleteException;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.AddressNotFoundException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.UpdateException;

@Service
public class AddressServiceImpl implements IAddressService{ 
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private IDistrictService districtService;

	@Override
	public Address create(Address address, String username) throws InsertException {
		//获取当前用户的UID
		Integer uid = address.getUid();
		//通过UID获取当前用户的收货地址数目
		Integer count = findCountByUid(uid);
		//判断是否应该为默认地址 
		address.setIsDefault(count==0?1:0);
		//获取省市区的中文名称
		String district = getDistrict(address.getProvince(), address.getCity(), address.getArea());
		address.setDistrict(district);
		
		Date now = new Date();
		address.setCreatedTime(now);
		address.setCreatedUser(username);
		address.setModifiedTime(now);
		address.setModifiedUser(username);
		
		addnew(address);
		return address;
	}
	
	@Override
	public List<Address> getAddressByUid(Integer id) {
		return findAddressByUid(id);
	}
	
	@Override
	@Transactional
	public void setDefault(Integer id, Integer uid, String username) throws UpdateException {
		//根据id查询收获地址数据
		Address data = findById(id);
		//判断数据是否为null
		if(data==null) {
			throw new AddressNotFoundException("设置默认收货地址失败！尝试访问的收获地址数据不存在！");
		}
		//判断查询到的数据中的uid与参数uid是否一致
		if(data.getUid()!=uid) {
			throw new AccessDeniedException("设置默认收货地址失败！访问数据权限验证不通过！");
		}
		
		//将该用户的所有收货地址设置为非默认
		updateNonDefault(uid);
		//将指定id的收货地址设置为默认
		updateDefault(id, username, new Date());
	};
	
	
	@Override
	@Transactional
	public void deleteAddress(Integer id,Integer uid,String username) throws DeleteException {
		//根据id查询收货地址数据
		Address data = findById(id);
		//删除数据是否为null
		if(data==null) {
			throw new AddressNotFoundException("删除失败！您需要删除的数据不存在！");
		}
		//检查数据归属是否有误
		if(data.getUid()!=uid) {
			throw new AccessDeniedException("删除失败！访问数据权限不正确！");
		}
		deleteById(id);
		//查询该用户还有没有收货地址
		Integer rows = findCountByUid(uid);
		if(rows>0) {
			//判断当前删除的是否为默认地址
			if(data.getIsDefault()==1) {
				//是默认地址，获取最后修改的收获地址
				Address lastAddress = findLastModified(uid);
				//设置最后修改的地址为默认地址
				setDefault(lastAddress.getId(), uid, username);
			}
		}
		
		
		
		
		
//		
//		//查看当前删除的是否为最后一条收货地址
//		Integer rows = findCountByUid(uid);
//		if(rows>1) {
//			//删除的不是最后一条
//			//判断当前删除的是否为默认地址
//			Address data = findById(id);
//			if(data.getIsDefault()==1) {
//				//当前删除的是默认地址
//				
//				//删除指定收货地址
//				deleteById(id);
//				
//				//获取该用户最后修改的收货地址
//				Address lastAddress = findLastModified(uid);
//				setDefault(lastAddress.getId(), uid, username);
//				return;
//			}
//		}
//			//是最后一条
//			//删除指定收货地址
//			deleteById(id);
		
	};
	
	/**
	 * 添加收货地址
	 * @param address 收货地址数据
	 */
	private void addnew(Address address) {
		Integer rows = addressMapper.addnew(address);
		if(rows!=1) {
			throw new InsertException("添加收货地址失败！出现未知错误！！");
		}
	}
	
	/**
	 * 根据用户ID查询该用户是否存在收货地址
	 * @param uid 用户ID
	 * @return 查询到的收货地址条数
	 */
	private Integer findCountByUid(Integer uid) {
		return addressMapper.findCountByUid(uid);
	}

	/**
	 * 	根据省市区的代号获取名称
	 * @param province 省的代号
	 * @param city 市的代号
	 * @param area 区的代号
	 * @return 省市区的名称，例如：浙江省杭州市上城区
	 */
	private String getDistrict(String province,String city,String area) {
		String provinceName = null;
		String cityName = null;
		String areaName = null;
		District p = districtService.getByCode(province);
		District c = districtService.getByCode(city);
		District a = districtService.getByCode(area);
		if(p!=null) {
			provinceName = p.getName();
		}
		if(c!=null) {
			cityName = c.getName();
		}
		if(a!=null) {
			areaName = a.getName();
		}
		return provinceName+cityName+areaName;
	}
	
	
	/**
	 * 查询该用户收货地址
	 * @param id 用户ID
	 * @return 该用户的收获地址
	 */
	private List<Address> findAddressByUid(Integer id){
		return addressMapper.findAddressByUid(id);
	}
	
	/**
	 * 将指定用户的默认地址全部设置为0(全都不默认)
	 */
	private void updateNonDefault(Integer uid) {
		Integer rows = addressMapper.updateNonDefault(uid);
		if(rows<1) {
			throw new UpdateException("设置非默认收获地址失败！出现未知错误！");
		}
	};
	
	/**
	 * 将指定地址设置为默认地址
	 */
	private void updateDefault(@RequestParam("id")Integer id,@RequestParam("username")String username,@RequestParam("date")Date date) {
		Integer rows = addressMapper.updateDefault(id, username, date);
		if(rows!=1) {
			throw new UpdateException("设置默认收获地址失败！出现未知错误！");
		}
	}
	
	/**
	 * 根据ID查询收获地址
	 * @param id 收货地址的ID
	 * @return 匹配的收货地址 若果没有匹配的数据则返回null
	 */
	private Address findById(Integer id) {
		return addressMapper.findById(id);
	};
	
	/**
	 * 删除指定的收货地址（根据ID）
	 */
	private void deleteById(Integer id) {
		Integer rows = addressMapper.deleteById(id);
		if(rows!=1) {
			throw new DeleteException("删除收货地址失败！出现未知错误！");
		}
	};
	/**
	 * 查询最后修改的收获地址
	 * @param uid
	 * @return
	 */
	private Address findLastModified(Integer uid) {
		return addressMapper.findLastModified(uid);
	}


	
}
