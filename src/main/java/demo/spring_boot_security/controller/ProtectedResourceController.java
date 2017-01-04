package demo.spring_boot_security.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
public class ProtectedResourceController {

    @RequestMapping("/{id}")
    public Object getOne(@PathVariable("id") String id) {
        return new String("Protected resource "+id);
    }
}