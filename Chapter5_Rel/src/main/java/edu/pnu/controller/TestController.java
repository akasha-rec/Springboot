package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class TestController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/boards")
	public List<Board> getBoards() {
		return boardRepo.findAll();
	}
	
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	
	@PostMapping("/board")
	public Board addBoard(Board b) {
		return boardRepo.save(b);
	}
	
	@PutMapping("/board")
	public Board updateBoard(Board b) {
		b.setCreateDate(new Date());
		return boardRepo.save(b);
	}
	
	@DeleteMapping("/board")
	public Board deleteBoard(Long seq) {
		Board b = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return b;
	}
}
