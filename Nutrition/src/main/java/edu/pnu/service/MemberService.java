package edu.pnu.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	
	// private final MemberRepository memberRepo;
	// PasswordEncoder encoder = new BCryptPasswordEncoder(); // 처음에는 private final PasswordEncoder encoder; 라고 해서 rawPassword cannot be null이라는 오류가 떴었는데 GPT에 물어보니 초기화하지 않아서 그럴 수도 있다고 함
	@Autowired MemberRepository memberRepo;
	@Autowired PasswordEncoder encoder; // PasswordEncoder 인터페이스의 구현체인 BCryptPasswordEncoder로 초기화는 했지만, 의존성 관리를 위해 @Autowired를 사용

	public boolean successMember(Member member) { // 회원가입
		if (memberRepo.existsByUserId(member.getUserId())) {
			return false;
		}
		else {
//			member.setAge(member.getAge()); // 사용자가 입력한 값이 이미 member 안에 값이 세팅되어있으므로 작성할 필요X
//			member.setGubun(member.getGubun());
//			member.setWeekNum(member.getWeekNum());
//			member.setUserId(member.getUserId());
			member.setPassword(encoder.encode(member.getPassword())); // 비밀번호는 DB에 저장하기 전에 암호화해야 하므로 작성해줘야 함.
			memberRepo.save(member);
			return true;
		}
	}
	
	public String findId(Member member) { // ID 찾기
		Member mem = memberRepo.findByPhoneNumber(member.getPhoneNumber());
		if (mem == null) {
			return "존재하지 않는 회원입니다.";
		}
		else {
			return mem.getUserId();
		}
	}
	
	public String findPassword(Member member) { // 비밀번호 찾기(임시 비밀번호 발급)
		Member mem = memberRepo.findByUserIdAndPhoneNumber(member.getUserId(), member.getPhoneNumber());
		
		if (mem == null) {
			return "존재하지 않는 회원입니다.";
		}
		else {
			String newPwd = RandomStringUtils.randomAlphanumeric(8);
			String encodedPwd = encoder.encode(newPwd);
			
			mem.setPassword(encodedPwd);
			memberRepo.save(mem);
			
			return newPwd; // 사용자에게 보여줄 임시 비밀번호
		}
	}
	

//	public String findPassword(Member member) {
//		Member mem = memberRepo.findByUserIdAndPhoneNumber(member.getUserId(), member.getPhoneNumber());
//		
//		if (mem == null) {
//			return "존재하지 않는 회원입니다.";
//		}
//		else {
//			mem.setPassword("abcd");
//			memberRepo.save(mem);
//			return "임시 비밀번호가 발급됐습니다.";
//		}
//	}
}
