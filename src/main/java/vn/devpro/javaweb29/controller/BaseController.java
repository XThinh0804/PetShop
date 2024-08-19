package vn.devpro.javaweb29.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.devpro.javaweb29.dto.Cart;
import vn.devpro.javaweb29.model.User;

@Controller
public class BaseController {
	@ModelAttribute("title")
	public String getTitle() {
		return "Pet's Shop";
	}
	@ModelAttribute("totalCartProducts")
	public BigInteger totalCartProducts(final HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("cart") == null) {//Không có giỏ hàng
			return BigInteger.ZERO;
		}else {
			//Có giỏ hàng
			Cart cart =(Cart) session.getAttribute("cart");
			return cart.totalCartProduct();
		}
		
	}
	@ModelAttribute("loginedUser")
	public User getLoginedUser() {
		Object loginedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(loginedUser != null && loginedUser instanceof UserDetails) {
			User user = (User) loginedUser;
			return user;
		}
		return new User();
	}
	//Kiểm tra đã login hay chưa?
	@ModelAttribute("isLogined")
	public boolean isLogined() {
		Object loginedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(loginedUser != null && loginedUser instanceof UserDetails) {
			return true;
		}
		return false;
	}
}
