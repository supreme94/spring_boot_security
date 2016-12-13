package demo.spring_boot_security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.spring_boot_security.entity.Subscriber;
import demo.spring_boot_security.repository.SubscriberRepository;

@Controller
public class RegistContoller {
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	@GetMapping("/regist")
	public String index(){
		return "regist/index";
	}
	
	@PostMapping("/regist")
	public String regist(@RequestParam Map<String, Object> params) {
		System.out.println(params.toString());
		Subscriber subscriber = new Subscriber();
		subscriber.setUsername(params.get("username").toString());
		subscriber.setPasswordHash(new BCryptPasswordEncoder().encode(params.get("password").toString()));
		subscriberRepository.save(subscriber);
		return "redirect:/login";
	}
	
}
