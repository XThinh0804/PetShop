package vn.devpro.javaweb29.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.devpro.javaweb29.model.User;

@Service
public class UserdetailsServiceJw29 extends BaseService<User> implements UserDetailsService{
	@Override
	public Class<User> clazz(){
		return User.class;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Lấy user trong tbl_user theo username (username từ form login)
		String sql = "SELECT * FROM tbl_user u WHERE u.username='"+username+"' and u.status=1";
		User user = this.getEntityByNativeSQL(sql);
		if(user != null) {
			return user;
		}else {
			return new User();//UserDetails khong duoc null
		}
	}
}
