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
//		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>(); 
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));  
//		User user = new User(subscriber.getUsername(),subscriber.getPasswordHash(),true,true,true, true, grantedAuthorities);  
//        return user; 
		return new CurrentUser(subscriber);
	}
	

}
