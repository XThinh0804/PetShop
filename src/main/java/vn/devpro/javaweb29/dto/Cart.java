package vn.devpro.javaweb29.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;


public class Cart {
	private ArrayList<CartProduct> cartProducts = new ArrayList<CartProduct>();
	//Tìm hàng trong giỏ
	public int findProductById(int productId) {
		for(int index = 0; index < this.cartProducts.size(); index ++) {
			if(cartProducts.get(index).getId() == productId) {
				return index;
			}
		}
		return -1;
	}
	//Tính tổng tiền của giỏ hàng
	public BigDecimal totalCartPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for(CartProduct cartProduct : this.cartProducts) {
			total = total.add(cartProduct.totalPrice());
		}
		return total;
	}
	//Tổng số lượng sản phẩm trong giỏ hàng
	public BigInteger totalCartProduct() {
		BigInteger total = BigInteger.ZERO;
		for(CartProduct cartProduct : this.cartProducts) {
			total = total.add(cartProduct.getQuantity());
		}
		return total;
	}
	
	public Cart() {
		super();
	}
	public Cart(ArrayList<CartProduct> cartProducts) {
		super();
		this.cartProducts = cartProducts;
	}
	public ArrayList<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(ArrayList<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
}
