package demo.spring_boot_security.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private Subscriber subscriber;
	
	public CurrentUser(Subscriber subscriber) {
        super(subscriber.getUsername(), subscriber.getPasswordHash(), AuthorityUtils.createAuthorityList("admin"));
        this.subscriber = subscriber;
    }

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public String getId() {
		return subscriber.getId();
	}

}
