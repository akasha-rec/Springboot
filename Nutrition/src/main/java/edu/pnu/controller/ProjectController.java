package edu.pnu.controller; // 사용자의 요청을 처리하고 적절한 응답을 반환하는 계층

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Nutrition;
import edu.pnu.dto.CateDTO;
import edu.pnu.service.ProjectService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody가 합쳐진 형태
public class ProjectController {
	
	private final ProjectService projectService;
	
	@GetMapping("/code1name") // 1. 대분류 목록 불러오기
	public List<String> getCode1name() {
		return projectService.getCode1name();
	}
	
	@GetMapping("/code2name/{Category1}") // 2. 대분류에 해당하는 중분류 불러오기
	public List<String> getCode2name(@PathVariable String Category1) {
		return projectService.getCode2name(Category1);
	}
	
	@GetMapping("/code3name/{Category1}/{Category2}") // 3. 대분류와 중분류에 해당하는 소분류 불러오기
	public List<String> getCode3name(@PathVariable String Category1, @PathVariable String Category2) {
		return projectService.getCode3name(Category1, Category2);
	}
	
	@GetMapping("/allSelect/{Category1}/{Category2}/{Category3}") // 4. 대분류, 중분류, 소분류 선택
	public List<CateDTO> getCode1nameCode2nameCode3nameFoodname(@PathVariable String Category1, @PathVariable String Category2, @PathVariable String Category3) {
		return projectService.Code1nameCode2nameCode3nameFoodname(Category1, Category2, Category3);
	}
	
	@GetMapping("/allSelect/{Category1}/{Category2}/{Category3}/{Keyword}") // 5. 대분류, 중분류, 소분류 O + Keyword 입력
	public List<CateDTO> getCode1nameCode2nameCode3nameKeywordFoodname(@PathVariable String Category1, @PathVariable String Category2, @PathVariable String Category3, @PathVariable String Keyword) {
		return projectService.Code1nameCode2nameCode3nameKeywordFoodname(Category1, Category2, Category3, Keyword);
	}
	
	@GetMapping("/foodname/{Category1}/{Category2}") // 6. 대분류, 중분류 선택
	public List<CateDTO> getCode1nameCode2nameFoodname(@PathVariable String Category1, @PathVariable String Category2) {
		return projectService.Code1nameCode2nameFoodname(Category1, Category2);
	}
	
	@GetMapping("/foodname/{Category1}/{Category2}/{Keyword}") // 7. 대분류, 중분류만 선택 + Keyword 입력
	public List<CateDTO> getCode1nameCode2nameKeywordFoodname(@PathVariable String Category1, @PathVariable String Category2, @PathVariable String Keyword) {
		return projectService.Code1nameCode2nameKeywordFoodname(Category1, Category2, Keyword);
	}
	
	@GetMapping("/foodname/{Keyword}") // 8. 키워드 입력
	public List<CateDTO> getKeywordFoodname(@PathVariable String Keyword) {
		return projectService.KeywordFoodname(Keyword);
	}
	
	@GetMapping("/nutri") // 9. id로 모든 상세정보 가져오기
	public Nutrition getNutri(Integer id) {
		return projectService.getNutri(id);
	}
}
