package vn.devpro.javaweb29.configurer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import aj.org.objectweb.asm.Handle;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	//Điều hướng đến request
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	//Chuyển đến request thích hợp với role khi xác thực thành công
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request,response,authentication);
		clearAuthenticationAttributes(request);
	}
	//Dựa vào role của user login để xác định request chuyển đến ( redirect)
	protected void handle(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException{
		String targetUrl = determineTargetUrl(authentication);
		//Lấy url theo role của user login
		if(response.isCommitted()) {
			return;
		}
//		System.out.println("targetUrl: "+targetUrl);
		redirectStrategy.sendRedirect(request, response, targetUrl);
		//Điều hướng target URL
	}
	//Lấy role của user và trả về url(action) tương ứng với authentication
	protected String determineTargetUrl(final Authentication authentication) throws IllegalStateException{
		Map<String, String> roleTargetUrlMap = new HashMap<String, String>();
		//Key la role name, value la URL (action)
		roleTargetUrlMap.put("ADMIN", "/admin/home/view");
		roleTargetUrlMap.put("GUEST", "/index");
		//Lấy danh sách các roles
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(final GrantedAuthority grantedAuthority : authorities) {
			//authorities lấy trong class User
			String authorityName = grantedAuthority.getAuthority();
			//role name
			if(roleTargetUrlMap.containsKey(authorityName)) {
				return roleTargetUrlMap.get(authorityName);
			}
		}
		throw new IllegalStateException();
	}
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session !=null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
