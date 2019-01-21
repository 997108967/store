package cn.tedu.store.entity;

/**
 * 购物车数据的实体类
 * @author soft01
 *
 */
public class Cart extends BaseEntity{

	private static final long serialVersionUID = -949791086205817020L;
	
	private Integer id;
	private Integer uid;
	private Integer gid;
	private Integer price;
	private Integer count;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer privce) {
		this.price = privce;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", uid=" + uid + ", gid=" + gid + ", privce=" + price + ", count=" + count + "]";
	}
	
}
