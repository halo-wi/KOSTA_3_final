package com.kosta.KOSTA_3_final.persistance.user;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.user.User;

public interface UserPersistance extends CrudRepository<User, Integer>{
	public Optional<User> findByEmail(String email);
	public User findByCustomerName(String userEmail);

	
}
