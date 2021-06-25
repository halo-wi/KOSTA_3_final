package com.kosta.KOSTA_3_final.persistance.user;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.user.Member;


public interface UserPersistance extends CrudRepository<Member, Long>{
   public Optional<Member> findByEmail(String email);
   public Member findByCustomerName(String userEmail);
	 public boolean existsByEmail(String email);



   
}
