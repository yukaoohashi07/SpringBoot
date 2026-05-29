package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import sample.common.service.LoginService;
import org.springframework.ui.Model;

@Controller
public class LoginController{
	
	private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("registerForm") RegisterForm form, BindingResult br) {
        if (br.hasErrors()) {
        	return "register";
        }

        loginService.register(form.getUsername(), form.getPassword());
        return "redirect:/login";
    }
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, 
						@RequestParam("password") String password,
						HttpSession session,
                        RedirectAttributes ra) {
        return loginService.authenticate(username, password)
      
            .map(user -> {
                session.setAttribute("loginUser", user);
                return "redirect:/tasks";
            })
            .orElseGet(() -> {
                ra.addFlashAttribute("error", "ユーザー名またはパスワードが違います");
                return "redirect:/login";
            });
    }
}
