package com.kosta.KOSTA_3_final.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.KOSTA_3_final.model.user.EmailDTO;
import com.kosta.KOSTA_3_final.model.user.User;
import com.kosta.KOSTA_3_final.persistance.user.UserPersistance;
import com.kosta.KOSTA_3_final.security.UserSecurity;




@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserPersistance repo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired 
	JavaMailSender sender;
	
	//회원 가입
	@Transactional
	public User joinUser(User member) {
		// 비밀번호 암호화...암호화되지않으면 로그인되지않는다.
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		// member.setMrole(MemberRoleEnumType.USER);
		System.out.println(passwordEncoder.encode(member.getPassword()));
		return repo.save(member);
	}
	

	
	
	
	 public EmailDTO createMailAndChangePassword(String userEmail, String userName){
	        String str = getTempPassword();
	        EmailDTO dto = new EmailDTO();
	        dto.setAddress(userEmail);
	        dto.setTitle(userName+"님의  임시비밀번호 안내 이메일 입니다.");
	        dto.setMessage("안녕하세요.  임시비밀번호 안내 관련 이메일 입니다." + "[" + userName + "]" +"님의 임시 비밀번호는 "
	        + str + " 입니다.");
	        updatePassword(str,userEmail);
	        return dto;
	    }
	
	 public String getTempPassword(){
	        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
	                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	        String str = "";

	        int idx = 0;
	        for (int i = 0; i < 10; i++) {
	            idx = (int) (charSet.length * Math.random());
	            str += charSet[idx];
	        }
	        return str;
	    }
	 
	  public void updatePassword(String str,String userEmail){
	        String pw = passwordEncoder.encode(str);
	        int id = repo.findByEmail(userEmail).get().getCustomer_id();
	        User member=repo.findById(id).get();
	        member.builder().password(pw).build();
	        repo.save(member);
	    }
	 
	public boolean userEmailCheck(String userEmail, String userName) {

        User user = repo.findByEmail(userEmail).get();
        if(user!=null && user.getCustomerName().equals(userName)) {
            return true;
        }
        else {
            return false;
        }
    }
	
	 @Async
	    public void sendEmail(SimpleMailMessage email) {
	        sender.send(email);
	    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails user=repo.findByEmail(email)
				.filter(m -> m != null).map(m -> new UserSecurity(m)).get();;
		
				
		return user;
	}

}
