package com.kosta.KOSTA_3_final.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserSecurity extends User{
	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX="ROLE_";
    private com.kosta.KOSTA_3_final.model.member.Member user;   
	public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public UserSecurity(com.kosta.KOSTA_3_final.model.member.Member user ) {	
		super(user.getEmail(),user.getPassword(),makeRole(user));
		this.user = user;
	}
	//Role을 여러개 가질수 있도록 되어있음 
	private static List<GrantedAuthority> makeRole(com.kosta.KOSTA_3_final.model.member.Member user) {
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getAuth()));
		return roleList;
	}

}