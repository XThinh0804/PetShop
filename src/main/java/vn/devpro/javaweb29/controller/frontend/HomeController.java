package vn.devpro.javaweb29.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb29.controller.BaseController;
import vn.devpro.javaweb29.model.Product;
import vn.devpro.javaweb29.model.ProductImage;
import vn.devpro.javaweb29.service.ProductImageService;
import vn.devpro.javaweb29.service.ProductService;
@Controller
public class HomeController extends BaseController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	//Action method cho index view
	@RequestMapping(value="/index",method =RequestMethod.GET)
	public String home(final Model model) {
		List<Product> products = productService.findAllActive();
		model.addAttribute("products",products);
		return "frontend/index";
	}
	
	@RequestMapping(value="/product-detail/{productId}",method =RequestMethod.GET)
	public String productDetail(final Model model,
			@PathVariable("productId") int productId) {
		//Lấy sản phẩm trong db
		Product product = productService.getById(productId);
		List<ProductImage> productImages = productImageService.getImageByProductId(productId);
		model.addAttribute("product",product);
		model.addAttribute("productImages",productImages);
		return "frontend/product-detail";
	}
}
