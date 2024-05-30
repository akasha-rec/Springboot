package edu.pnu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.board.domain.Board;
import edu.pnu.board.security.SecurityUser;
import edu.pnu.board.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model, Board board) {
		Page<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "board/getBoard";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard") // @AuthenticationPrincipal 추가 > SecurityUser 객체가 할당
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) { // 로그인 성공한 회원 객체를 가지고 있는 SecurityUser 객체를 매개변수로
		
		board.setMember(principal.getMember()); // SecurityUser 객체에서 Member 엔티티를 꺼내서 세팅
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@PostMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
