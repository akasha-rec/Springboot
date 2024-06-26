package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		return ResponseEntity.ok(detailResult);
		
//		selectResultService.saveSelectResult(detailResult);
//		idSetService.saveIdset(detailResult);
//		nutriResultService.saveNutriResult(detailResult);
		
	}
	
}
