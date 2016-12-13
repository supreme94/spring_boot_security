package demo.spring_boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping(path = "/login")
	public String index() {
		return "login/index";
	}
	
	
}
