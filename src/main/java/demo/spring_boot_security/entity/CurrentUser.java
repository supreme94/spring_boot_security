package demo.spring_boot_security.entity;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private Subscriber subscriber;
	
	public CurrentUser(Subscriber subscriber) {
        super(subscriber.getUsername(), subscriber.getPasswordHash(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        this.subscriber = subscriber;
    }

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public String getId() {
		return subscriber.getId();
	}

}
