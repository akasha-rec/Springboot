package edu.pnu.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.MyMemoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemoController {
	
	private final MyMemoService memoService;

}
