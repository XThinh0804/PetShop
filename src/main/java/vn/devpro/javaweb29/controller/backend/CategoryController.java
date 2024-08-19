package vn.devpro.javaweb29.controller.backend;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb29.controller.BaseController;
import vn.devpro.javaweb29.model.Category;
import vn.devpro.javaweb29.model.User;
import vn.devpro.javaweb29.service.CategoryService;
import vn.devpro.javaweb29.service.UserService;

@Controller
@RequestMapping("/admin/category/")
public class CategoryController extends BaseController{
	//khai báo service
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "list", method =RequestMethod.GET)
	public String viewCategories(final Model model) {
		List<Category> categories = new ArrayList<Category>();
//		categories = categoryService.findAll();
		categories = categoryService.findAllActive();//Chỉ hiện thị những category status=true
		model.addAttribute("categories",categories);
		return "backend/category-list";
	}
	
	@RequestMapping(value = "add", method =RequestMethod.GET)
	public String addCategories(final Model model) {
		List<User> users = userService.findAll();
		Category category = new Category();
		category.setCreateDate(new Date());
		model.addAttribute("category",category);
		model.addAttribute("users",users);
		return "backend/category-add";
	}
	
	@RequestMapping(value = "add-save", method =RequestMethod.POST)
	public String saveAddCategories(final Model model,
			@ModelAttribute("category") Category category) {
		categoryService.saveOrUpdate(category);
		return "redirect:add";
	}
	
	@RequestMapping(value = "edit/{categoryId}", method =RequestMethod.GET)
	public String editCategories(final Model model,
			@PathVariable("categoryId") int categoryId) {
		Category category = categoryService.getById(categoryId);
		List<User> users = userService.findAll();
		model.addAttribute("category",category);
		model.addAttribute("users",users);
		return "backend/category-edit";
	}
	
	@RequestMapping(value = "edit-save", method =RequestMethod.POST)
	public String saveEditCategories(final Model model,
			@ModelAttribute("category") Category category) {
		categoryService.saveOrUpdate(category);
		return "redirect:list";
	}
	
//	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
//	public String deleteCategories(final Model model,
//			@PathVariable("categoryId") int categoryId) {
//		
//		categoryService.deleteCategory(categoryId);
//		return "backend/category-list";
//	}
	
	//Inactive:Bthg k nên xóa dữ liệu chỉ nên ẩn dữ liệu
	@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
	public String deleteCategories(final Model model,
			@PathVariable("categoryId") int categoryId) {
		
		Category category = categoryService.getById(categoryId);
		category.setStatus(false);
		categoryService.saveOrUpdate(category);
		return "redirect:/admin/category/list";
	}
}
