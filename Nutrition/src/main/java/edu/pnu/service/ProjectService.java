// 비즈니스 로직을 포함하고, 컨트롤러로부터 받은 요청을 처리하기 위해 DB와 상호작용하는 계층
// Repository 객체를 사용하여 데이터 액세스 관리하고, 데이터를 가공하거나 필요한 형태로 조작
package edu.pnu.service;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Nutrition;
import edu.pnu.dto.CateDTO;
import edu.pnu.persistence.NutriRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProjectService {
	
	private final NutriRepository nutriRepo;
	
	public List<String> getCode1name() { // 1. 대분류 목록 불러오기
		return nutriRepo.findDistinctCode1name();
	}
	
	public List<String> getCode2name(String Category1) { // 2. 대분류에 해당하는 중분류 불러오기
		return nutriRepo.findDistinctCode2nameByCode1nameContaining(Category1);
	}
	
	public List<String> getCode3name(String Category1, String Category2) { // 3. 대분류와 중분류에 해당하는 소분류 가져오기
		return nutriRepo.findDistinctCode3nameByCode1nameCode2nameContaining(Category1, Category2);
	}
	
	// DTO로 사용자에게 필요한 정보를 제공할 때 데이터베이스의 전체가 아닌 일부만 가져오고자 할 때 
	public List<CateDTO> Code1nameCode2nameCode3nameFoodname(String Category1, String Category2, String Category3) { // 4. 대분류, 중분류, 소분류 선택
		List<Object[]> rst = nutriRepo.findCode1nameCode2nameCode3nameFoodname(Category1, Category2, Category3); // 쿼리 결과가 담겨있는 Object 타입의 배열을 담고 있는 리스트 > [id, 대분류명, 중분류명, 소분류명, 식사유형, 식품명]
		List<CateDTO> reslt = new ArrayList<>(); // return할 CateDTO 객체 담을 리스트 만들어주면서 초기화
		for (Object[] arr2 : rst) { // 쿼리 Object[]을 CateDTO 객체로 변환
			reslt.add(CateDTO.builder()
					.id((Integer)arr2[0])
					.code1name((String)arr2[1])
					.code2name((String)arr2[2])
					.code3name((String)arr2[3])
					.originname((String)arr2[4])
					.foodname((String)arr2[5]).build());
		}
		return reslt; // 비즈니스 로직 분리, front에게 일관된 구조를 가진 데이터 전달 가능, 코드의 가독성과 보수 유지성이 좋아진다는 장점이 있다고 함.
	}
	
	public List<CateDTO> Code1nameCode2nameCode3nameKeywordFoodname(String Category1, String Category2, String Category3, String Keyword) { // 5. 대분류, 중분류, 소분류 선택 + Keyword 입력
		List<Object[]> rs =  nutriRepo.findCode1nameCode2nameCode3nameKeywordFoodname(Category1, Category2, Category3, Keyword);
		List<CateDTO> result = new ArrayList<>();
		for (Object[] arr1 : rs) {
			result.add(CateDTO.builder()
					.id((Integer)arr1[0])
					.code1name((String)arr1[1])
					.code2name((String)arr1[2])
					.code3name((String)arr1[3])
					.originname((String)arr1[4])
					.foodname((String)arr1[5]).build());
		}
		return result;
	}
	
	public List<CateDTO> Code1nameCode2nameFoodname(String Category1, String Category2) { // 6. 대분류, 중분류
		List<Object[]> rs1 = nutriRepo.findCode1nameCode2nameFoodname(Category1, Category2);
		List<CateDTO> result1 = new ArrayList<>();
		for (Object[] arr3 : rs1) {
			result1.add(CateDTO.builder()
					.id((Integer)arr3[0])
					.code1name((String)arr3[1])
					.code2name((String)arr3[2])
					.code3name((String)arr3[3])
					.originname((String)arr3[4])
					.foodname((String)arr3[5]).build());
		}
		return result1;
	}

	public List<CateDTO> Code1nameCode2nameKeywordFoodname(String Category1, String Category2, String Keyword) { // 7. 대분류, 중분류만 선택 + Keyword 입력
		List<Object[]> res = nutriRepo.findCode1nameCode2nameKeywordFoodname(Category1, Category2, Keyword);
		List<CateDTO> ret = new ArrayList<>();
		for (Object[] arr : res) {
			ret.add(CateDTO.builder()
					.id((Integer)arr[0])
					.code1name((String)arr[1])
					.code2name((String)arr[2])
					.code3name((String)arr[3])
					.originname((String)arr[4])
					.foodname((String)arr[5]).build());
		}
		return ret;
	}
	


	public List<CateDTO> KeywordFoodname(String Keyword) { // 8. 키워드만 입력
		List<Object[]> res1 = nutriRepo.findKeywordFoodname(Keyword);
		List<CateDTO> ret1 = new ArrayList<>();
		for (Object[] arr1 : res1) {
			ret1.add(CateDTO.builder()
					.id((Integer)arr1[0])
					.code1name((String)arr1[1])
					.code2name((String)arr1[2])
					.code3name((String)arr1[3])
					.originname((String)arr1[4])
					.foodname((String)arr1[5]).build());
		}
		return ret1;
	}
	
	public Nutrition getNutri(Integer id) { // 9. id로 모든 상세정보를 가져오기
		return nutriRepo.findById(id).orElseThrow(); // id의 존재 여부를 안전하게 처리하고, NullPointException을 쉽게 처리하기 위해
	}
	
}
