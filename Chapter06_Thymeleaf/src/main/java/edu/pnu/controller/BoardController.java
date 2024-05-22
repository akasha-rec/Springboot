package edu.pnu.controller; //실제 이런 코드는 없고 필터를 사용해서 인증

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member") //세션에 상태 정보 저장할 때 사용 > Model에 member라는 이름으로 저장된 데이터를 자동으로 세션에 등록
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("member") //Model 객체에다가 return하여 생성된 객체를 member라는 이름으로 저장해라 > save! 로그인하든 하지 않든 비어있는 객체 생성 + 초기화
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) { //세션에 저장된 member 객체를 찾아서 넣어줌
		
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		List<Board> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) { //데이터 저장해서 View에다가 넘겨
//		
//		List<Board> boardList = new ArrayList<>();
//		for (long i = 1L; i <= 10L; i++) {
//			boardList.add(Board.builder()
//					.seq(i)
//					.title("게시판 프로그램 테스트")
//					.writer("도우너")
//					.content("게시판 프로그램 테스트입니다.")
//					.build());
//		}
//		model.addAttribute("boardList", boardList);
//		return "getBoardList"; //이름을 리턴해서 String으로 선언
//	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard"; //view name return
	}
	
//	@GetMapping("/getBoard")
//	public ModelAndView getBoard(Member member, Board board) {
//		Board b = boardService.getBoard(board);
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("board", b);
//		mv.setViewName("getBoard");
//		return mv;
//	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
}
