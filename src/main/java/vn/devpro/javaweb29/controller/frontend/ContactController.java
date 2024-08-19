package vn.devpro.javaweb29.controller.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.devpro.javaweb29.controller.BaseController;
import vn.devpro.javaweb29.dto.Contact;
import vn.devpro.javaweb29.dto.Jw29Contant;
@RequestMapping("/contact/")
@Controller
public class ContactController extends BaseController 
								implements Jw29Contant {
		@RequestMapping(value="/view",method =RequestMethod.GET)
		public String contactView() {
			return "frontend/contact/contact";
		}
		//Doc du lieu tu form
		@RequestMapping(value="/send",method =RequestMethod.POST)
		public String contactSend(final HttpServletRequest request) {
			Contact contacts = new Contact(
					request.getParameter("txtName"),
					request.getParameter("txtEmail"),
					request.getParameter("txtMobile"),
					request.getParameter("txtAddress"),
					request.getParameter("txtMessage"));
			//Luu vao database
			System.out.println("Name: " + contacts.getName());
			System.out.println("Email: " + contacts.getEmail());
			System.out.println("Mobile: " + contacts.getMobile());
			System.out.println("Address: " + contacts.getAddress());
			System.out.println("Message: " + contacts.getMessage());
			return "frontend/contact/contact";
			
		}
		//Day du lieu vao form
		@RequestMapping(value = "edit", method = RequestMethod.GET)
		public String contactEdit(final Model model) {
			Contact contact = new Contact("Anh Thinh","thinha4k54cma@gmail.com","0984955623","Chuong My-Ha Noi","Doi tra hang");
			//Day du lieu vao view
			model.addAttribute("contact",contact);
			return "frontend/contact/contact-edit";
		}
		//Doc du lieu tu form
//				@RequestMapping(value="save-edit",method =RequestMethod.POST)
//				public String contactSave(
//						final Model model,
//						final HttpServletRequest request) {
//					Contact contacts = new Contact(
//							request.getParameter("txtName"),
//							request.getParameter("txtEmail"),
//							request.getParameter("txtMobile"),
//							request.getParameter("txtAddress"),
//							request.getParameter("txtMessage"));
//					//Luu vao database
//					System.out.println("Name: " + contacts.getName());
//					System.out.println("Email: " + contacts.getEmail());
//					System.out.println("Mobile: " + contacts.getMobile());
//					System.out.println("Address: " + contacts.getAddress());
//					System.out.println("Message: " + contacts.getMessage());
//					String message = "Cam on ban da gui thong tin";
//					model.addAttribute("message", message);
//					return "frontend/contact/contact";
//					
//				}
				@RequestMapping(value="save-edit",method = RequestMethod.POST)
				public ResponseEntity<Map<String, String>> contactSave(
						final Model model,
						final HttpServletRequest request,
						@RequestBody Contact contact) {
					//Lưu dữ liệu vào DB(chưa làm)
					Map<String, String> jsonResult = new HashMap<String, String>();
					jsonResult.put("code", "200");
					jsonResult.put("message", "Cảm ơn bạn " +
							contact.getEmail() + " đã gửi thông tin");
					return ResponseEntity.ok(jsonResult);
					
				}
				@RequestMapping(value="/view-sf",method =RequestMethod.GET)
				public String contactViewSf(final Model model) {
					Contact contact = new Contact();
					model.addAttribute("contact", contact);
					return "frontend/contact/contact-sf";
				}
				@RequestMapping(value="/send-sf",method =RequestMethod.POST)
				public String contactSendSf(final Model model,
						@RequestParam("contactFile") MultipartFile file,
						@ModelAttribute("contact") Contact contact) throws IOException{
					//Lưu dữ liệu vào db(Chưa làm)
					model.addAttribute("message","Cảm ơn bạn "+contact.getName()+" đã gửi phản hồi");
					//Lưu file mà người dùng upload
					//Kiểm tra người dùng có upload file hay không
					if(file != null && !file.getOriginalFilename().isEmpty()) {
						//Có upload file
						String path = FOLDER_UPLOAD + "ContactFiles/" + file.getOriginalFilename();
						File fp = new File(path);
						file.transferTo(fp);
					}
					return "frontend/contact/contact-sf";
				}
				@RequestMapping(value="/edit-sf",method =RequestMethod.GET)
				public String contactEditSf(final Model model) {
					Contact contact = new Contact("Anh Thinh","thinha4k54cma@gmail.com","0984955623","Chuong My-Ha Noi","Doi tra hang");
					model.addAttribute("contact", contact);
					return "frontend/contact/contact-sf-edit";
				}
}
