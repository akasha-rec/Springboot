package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dto.DetailResultDTO;
import edu.pnu.service.IdSetService;
import edu.pnu.service.NutriResultService;
import edu.pnu.service.SelectResultService;

@RestController
public class NutriController {
	
	@Autowired
	private IdSetService idSetService;
	
	@Autowired
	private NutriResultService nutriResultService;

	@Autowired
	private SelectResultService selectResultService;
	
	@PostMapping("/userdata")
	public ResponseEntity<?> resultSave(@RequestBody DetailResultDTO detailResult) {
		System.out.println(detailResult);
		
		idSetService.saveIdset(detailResult);
		nutriResultService.saveNutriResult(detailResult);
		selectResultService.saveSelectResult(detailResult);
		
		return ResponseEntity.ok(detailResult);
	}
}
