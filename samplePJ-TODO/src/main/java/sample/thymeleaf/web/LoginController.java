package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;

@Controller
public class LoginController{
	
	@Autowired
	private LoginMapper loginMapper;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(Login login) {
		loginMapper.insert(login);
		return "redirect:/login";
}
}