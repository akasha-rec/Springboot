package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepo;
	
	public List<Board> getAllBoards() {
		return boardRepo.findAll();
	}
	
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	
	public Board addBoard(Board b) {
		return boardRepo.save(b);
	}
	
	public Board updateBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.update(board);
		return boardRepo.save(b);
	}
	
	public Board deleteBoard(Long seq) {
		Board b = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return b;
	}
}
