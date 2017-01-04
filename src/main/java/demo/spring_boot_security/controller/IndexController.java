package demo.spring_boot_security.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(path={"/", "/index"})
	public String index(HttpSession session) {
		session.setAttribute("a", "b");
		return "index/index";
	}
	
}
