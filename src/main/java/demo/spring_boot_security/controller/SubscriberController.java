package demo.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import demo.spring_boot_security.repository.SubscriberRepository;

@Controller
public class SubscriberController {
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	@GetMapping("/subscriber/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String index(@PathVariable("id") String id){
		return "subscriber/index";
	}
	
}
