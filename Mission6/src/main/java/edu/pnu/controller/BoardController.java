package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/boards")
	public List<Board> getAllBoards() {
		return boardService.getAllBoards();
	}
	
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardService.getBoard(seq);
	}
	
	@PostMapping("/board")
	public Board addBoard(Board b) {
		return boardService.addBoard(b);
	}
	
	@PutMapping("/board")
	public Board updateBoard(Board b) {
		return boardService.updateBoard(b);
	}
	
	@DeleteMapping("/board")
	public Board deleteBoard(Long seq) {
		return boardService.deleteBoard(seq);
	}
}
