package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取session对象
		HttpSession session = request.getSession();
		//判断session中是否存在id属性
		if(session.getAttribute("id")==null) {
			//没有 没登录 false
			response.sendRedirect("../web/login.html");
			return false;
		}
		//有id 登录状态 true
		return true;
	}
	
}
