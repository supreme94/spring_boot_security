package demo.spring_boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.spring_boot_security.entity.CurrentUser;
import demo.spring_boot_security.entity.Subscriber;
import demo.spring_boot_security.repository.SubscriberRepository;

@Service
public class SubscriberService implements UserDetailsService {
	
	@Autowired
	SubscriberRepository subscriberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Subscriber subscriber = subscriberRepository.findOneByUsername(username);
		return new CurrentUser(subscriber);
	}
	

}
