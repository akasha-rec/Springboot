package edu.pnu.service; //Repository를 의존성 주입하여 사용

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	public List<Board> getBoardList(Board board) {
		return (List<Board>) boardRepo.findAll();
	}
	
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}
	
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}
	
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}
	
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}
}
