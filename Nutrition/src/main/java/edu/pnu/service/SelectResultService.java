package edu.pnu.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import edu.pnu.domain.SelectResult;
import edu.pnu.dto.DetailResultDTO;
import edu.pnu.persistence.SelectResultRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SelectResultService {
	
	private final SelectResultRepository selectResultRepo;
	
	public void saveSelectResult(DetailResultDTO detailResult) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else if (principal instanceof String) {
			username = (String) principal;
		} else {
			// 처리할 수 없는 경우, 예외 처리 또는 기본값 설정
			throw new IllegalStateException("Unknown principal type: " + principal.getClass());
		}
		
		// User user = (User) authentication.getPrincipal(); 월요일에 여쭤보기
		// class java.lang.String cannot be cast to class org.springframework.security.core.userdetails.User (java.lang.String is in module java.base of loader 'bootstrap'; org.springframework.security.core.userdetails.User is in unnamed module of loader 'app')
		
		SelectResult selectResult = new SelectResult();
		selectResult.setRid(detailResult.getNum());
		selectResult.setTitle(detailResult.getTitle());
		selectResult.setUser_id(username);
		selectResult.setAge(detailResult.getAge());
		selectResult.setCondition1(detailResult.getCondition1());
		selectResult.setCondition2(detailResult.getCondition2());
//		selectResult.setResultdate(detailResult.getTimeStamp());
		
		selectResultRepo.save(selectResult);
	}
}
