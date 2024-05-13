package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;

@RestController
public class BoardController {
	
	@GetMapping("/hello")
	public String Hello(String name) { //String result = restTemplate.getForObject("/hello?name=둘리", String.class);에서 파라미터가 있어서 넣어주고
		return "Hello : " +  name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() { //BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class); 파라미터가 없어서 필요X
		BoardVO m = new BoardVO();
		m.setWriter("테스터");
		return m;
	}
}
