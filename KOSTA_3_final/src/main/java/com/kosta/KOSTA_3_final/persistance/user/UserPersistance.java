package com.kosta.KOSTA_3_final.persistance.user;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.member.Member;

public interface UserPersistance extends CrudRepository<Member, Integer>{
	public Optional<Member> findByEmail(String email);
	public Member findByCustomerName(String userEmail);

	
}
