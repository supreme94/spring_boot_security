package demo.spring_boot_security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import demo.spring_boot_security.entity.CurrentUser;

//@ControllerAdvice
//public class CurrentUserController {
//	
//	@ModelAttribute("currentUser")
//    public CurrentUser getCurrentUser(Authentication authentication) {
//		System.out.println("current user....");
//		System.out.println("authentication " + authentication);
//        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
//    }
//}
