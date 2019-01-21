package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.util.ResponseResult;
import cn.tedu.store.vo.CartVO;

/**
 * 购物车数据的控制器类
 * @author soft01
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{
	
	@Autowired
	private ICartService cartService;
	
	@PostMapping("/add_to_cart")
	public ResponseResult<Void> addCart(Cart cart,HttpSession session){
		String username = session.getAttribute("username").toString();
		Integer uid = getIdFromSession(session);
		cart.setUid(uid);
		cartService.addToCart(cart, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("/list")
	public ResponseResult<List<CartVO>> getByUid(HttpSession session){
		Integer uid = getIdFromSession(session);
		List<CartVO> list = cartService.getByUid(uid);
		return new ResponseResult<List<CartVO>>(SUCCESS,list);
	}
	
	@GetMapping("/add_count")
	public ResponseResult<Void> addCount(HttpSession session,@RequestParam("id")Integer id){
		Integer uid = getIdFromSession(session);
		String username = session.getAttribute("username").toString();
		cartService.addCount(id, uid, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/get_by_ids")
	public ResponseResult<List<CartVO>> getByIds(@RequestParam("cart_id")Integer[] ids){
		List<CartVO> list = cartService.getByIds(ids);
		return new ResponseResult<List<CartVO>>(SUCCESS,list);
	}
}
