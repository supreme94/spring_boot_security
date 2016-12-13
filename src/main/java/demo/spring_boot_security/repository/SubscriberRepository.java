package demo.spring_boot_security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.spring_boot_security.entity.Subscriber;

public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
	public Subscriber findOneByUsername(String username);
}
