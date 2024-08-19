package vn.devpro.javaweb29.controller.backend;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.devpro.javaweb29.controller.BaseController;

@Controller
@RequestMapping("/admin/home/")
public class HomeAdminController extends BaseController{
	@RequestMapping(value = "view", method =RequestMethod.GET)
	public String viewHomePage(final Model model) {
		
		return "backend/home";
	}
}
