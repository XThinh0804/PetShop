package vn.devpro.javaweb29.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "tbl_user")
public class User extends BaseModel implements UserDetails{
	@Column(name = "username", length = 120, nullable = false)
	private String username;

	@Column(name = "password", length = 120, nullable = false)
	private String password;

	@Column(name = "name", length = 120, nullable = true)
	private String name;

	@Column(name = "email", length = 200, nullable = true)
	private String email;

	@Column(name = "mobile", length = 60, nullable = true)
	private String mobile;

	@Column(name = "address", length = 300, nullable = true)
	private String address;

	@Column(name = "avatar", length = 300, nullable = true)
	private String avatar;

	@Column(name = "description", length = 500, nullable = true)
	private String description;

//---------------Mapping many-to-many: tbl_user-to-tbl_role---------------------------
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private List<Role> roles = new ArrayList<Role>();

	// Add and remove elements out of relational user-role list
	public void addRelationalUserRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}

	public void removeRelationalUserRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}

	// -----------Mapping many-to-one: user-to-user------------------
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "create_by")
	private User userCreateUser;
//-----------Mapping many-to-one: user-to-user------------------
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "update_by")
	private User userUpdateUser;
	// ---------Mapping one-to-many: tbl_user-to-tbl_user-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateUser")
	private List<User> createUsers = new ArrayList<User>();

	// ---------Mapping one-to-many: tbl_user-to-tbl_user-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateUser")
	private List<User> updateUsers = new ArrayList<User>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<SaleOrder> saleOrders = new ArrayList<SaleOrder>();

	// Methods add and remove elements in relational product list
	public void addRelationalSaleOrder(SaleOrder saleOrder) {
		saleOrders.add(saleOrder);
		saleOrder.setUser(this);
	}

	public void removeRelationalSaleOrderProduct(SaleOrder saleOrder) {
		saleOrders.remove(saleOrder);
		saleOrder.setUser(null);
	}

	// ---------Mapping one-to-many: tbl_user-to-tbl_category-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateCategory")
	private List<Category> createCategories = new ArrayList<Category>();

	// ---------Mapping one-to-many: tbl_user-to-tbl_category-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateCategory")
	private List<Category> updateCategories = new ArrayList<Category>();
	// ---------Mapping one-to-many: tbl_user-to-tbl_product-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProduct")
	private List<Product> createProducts = new ArrayList<Product>();

	// ---------Mapping one-to-many: tbl_user-to-tbl_product-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProduct")
	private List<Product> updateProducts = new ArrayList<Product>();

	// ---------Mapping one-to-many: tbl_user-to-tbl_sale_order-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateSaleOrder")
	private List<SaleOrder> createSaleOrders = new ArrayList<SaleOrder>();

	// ---------Mapping one-to-many: tbl_user-to-tbl_sale_order-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateSaleOrder")
	private List<SaleOrder> updateSaleOrders = new ArrayList<SaleOrder>();
	// ---------Mapping one-to-many: tbl_user-to-tbl_sale_order-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateSaleOrderProduct")
	private List<SaleOrderProduct> createSaleOrderProducts = new ArrayList<SaleOrderProduct>();

	// ---------Mapping one-to-many: tbl_user-to-tbl_sale_order-----------
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateSaleOrderProduct")
	private List<SaleOrderProduct> updateSaleOrderProducts = new ArrayList<SaleOrderProduct>();

	public User() {

	}

	public User(Integer id, Date createDate, Date updateDate, Boolean status, String username, String password,
			String name, String email, String mobile, String address, String avatar, String description,
			List<Role> roles, User userCreateUser, User userUpdateUser, List<User> createUsers, List<User> updateUsers,
			List<SaleOrder> saleOrders, List<Category> createCategories, List<Category> updateCategories,
			List<Product> createProducts, List<Product> updateProducts, List<SaleOrder> createSaleOrders,
			List<SaleOrder> updateSaleOrders, List<SaleOrderProduct> createSaleOrderProducts,
			List<SaleOrderProduct> updateSaleOrderProducts) {
		super(id, createDate, updateDate, status);
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.avatar = avatar;
		this.description = description;
		this.roles = roles;
		this.userCreateUser = userCreateUser;
		this.userUpdateUser = userUpdateUser;
		this.createUsers = createUsers;
		this.updateUsers = updateUsers;
		this.saleOrders = saleOrders;
		this.createCategories = createCategories;
		this.updateCategories = updateCategories;
		this.createProducts = createProducts;
		this.updateProducts = updateProducts;
		this.createSaleOrders = createSaleOrders;
		this.updateSaleOrders = updateSaleOrders;
		this.createSaleOrderProducts = createSaleOrderProducts;
		this.updateSaleOrderProducts = updateSaleOrderProducts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUserCreateUser() {
		return userCreateUser;
	}

	public void setUserCreateUser(User userCreateUser) {
		this.userCreateUser = userCreateUser;
	}

	public User getUserUpdateUser() {
		return userUpdateUser;
	}

	public void setUserUpdateUser(User userUpdateUser) {
		this.userUpdateUser = userUpdateUser;
	}

	public List<User> getCreateUsers() {
		return createUsers;
	}

	public void setCreateUsers(List<User> createUsers) {
		this.createUsers = createUsers;
	}

	public List<User> getUpdateUsers() {
		return updateUsers;
	}

	public void setUpdateUsers(List<User> updateUsers) {
		this.updateUsers = updateUsers;
	}

	public List<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(List<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public List<Category> getCreateCategories() {
		return createCategories;
	}

	public void setCreateCategories(List<Category> createCategories) {
		this.createCategories = createCategories;
	}

	public List<Category> getUpdateCategories() {
		return updateCategories;
	}

	public void setUpdateCategories(List<Category> updateCategories) {
		this.updateCategories = updateCategories;
	}

	public List<Product> getCreateProducts() {
		return createProducts;
	}

	public void setCreateProducts(List<Product> createProducts) {
		this.createProducts = createProducts;
	}

	public List<Product> getUpdateProducts() {
		return updateProducts;
	}

	public void setUpdateProducts(List<Product> updateProducts) {
		this.updateProducts = updateProducts;
	}

	public List<SaleOrder> getCreateSaleOrders() {
		return createSaleOrders;
	}

	public void setCreateSaleOrders(List<SaleOrder> createSaleOrders) {
		this.createSaleOrders = createSaleOrders;
	}

	public List<SaleOrder> getUpdateSaleOrders() {
		return updateSaleOrders;
	}

	public void setUpdateSaleOrders(List<SaleOrder> updateSaleOrders) {
		this.updateSaleOrders = updateSaleOrders;
	}

	public List<SaleOrderProduct> getCreateSaleOrderProducts() {
		return createSaleOrderProducts;
	}

	public void setCreateSaleOrderProducts(List<SaleOrderProduct> createSaleOrderProducts) {
		this.createSaleOrderProducts = createSaleOrderProducts;
	}

	public List<SaleOrderProduct> getUpdateSaleOrderProducts() {
		return updateSaleOrderProducts;
	}

	public void setUpdateSaleOrderProducts(List<SaleOrderProduct> updateSaleOrderProducts) {
		this.updateSaleOrderProducts = updateSaleOrderProducts;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
