package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dto.DetailResultDTO;
import edu.pnu.service.ResultSaveService;

@RestController
public class NutriController {
	
//	@Autowired 뭣도 모르고 호기롭게 Controller 클래스에서도 Save 했었다...
//	private IdSetService idSetService;
//	
//	@Autowired
//	private NutriResultService nutriResultService;
//
//	@Autowired
//	private SelectResultService selectResultService;
	
	@Autowired
	private ResultSaveService resultService;
	
	@PostMapping("/userdata")
	public ResponseEntity<?> resultSave(@RequestBody DetailResultDTO detailResult) {
		resultService.resultSave(detailResult);
		return ResponseEntity.ok().build();
		
//		selectResultService.saveSelectResult(detailResult);
//		idSetService.saveIdset(detailResult);
//		nutriResultService.saveNutriResult(detailResult);
		
	}
	// +) /MyPage에서 메모도 같이 보여져야 하는데 어떻게 해야 하는지?
	@GetMapping("/MyPage") // jwt 토큰에서 로그인한 사용자 아이디를 읽어와서 원래 파라미터로 있더 String userId를 지웠다면 앞에 있던 Repository, Service 클래스는 그대로 둬도 되는 걸까?
	public ResponseEntity<?> nutriMypage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		return ResponseEntity.ok(resultService.sendMyPage(user.getUsername()));
	}
	
	@GetMapping("/MyNutri/{rid}")
	public ResponseEntity<?> nutriMypageByRid(@PathVariable Integer rid) {
		return ResponseEntity.ok(resultService.sendMyPageByRid(rid));
	}
}
