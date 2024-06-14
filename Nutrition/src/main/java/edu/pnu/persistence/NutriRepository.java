// DB와 상호 작용을 추상화하고 데이터 액세스 기능을 제공 + JPA를 사용할 때 주로 사용
// DB와 직접 상호작용하는 로직을 캡슐화하며 DB 데이터를 CRUD하는 작업 처리 + Entity 클래스를 사용해서 데이터 조작
// Repository 패턴은 인터페이스 기반으로 데이터 액세스 기능을 정의하며 구현체는 프레임워크가 자동으로 생성
package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Nutrition;

@Repository
public interface NutriRepository extends JpaRepository<Nutrition, Integer> {
	
	@Query("SELECT DISTINCT n.code1name FROM Nutrition n") // 1. 대분류 목록 불러오기
	List<String> findDistinctCode1name();
	
	@Query("SELECT DISTINCT n.code2name FROM Nutrition n WHERE n.code1name=:Category1") // 2. 대분류에 해당하는 중분류 불러오기
	List<String> findDistinctCode2nameByCode1nameContaining(@Param("Category1") String Category1);
	
	@Query("SELECT DISTINCT n.code3name FROM Nutrition n WHERE n.code1name=:Category1 and n.code2name=:Category2") // 3. 대분류와 중분류에 해당하는 소분류 가져오기
	List<String> findDistinctCode3nameByCode1nameCode2nameContaining(@Param("Category1") String Category1, @Param("Category2") String Category2);
	
//	@Query("SELECT DISTINCT n.code3name FROM Nutrition n WHERE n.code1name LIKE %:Category1% and n.code2name LIKE %:Category2%") // 3. 대분류와 중분류에 해당하는 소분류 가져오기
//	List<String> findDistinctCode3nameByCode1nameCode2nameContaining(@Param("Category1") String Category1, @Param("Category2") String Category2);
	
	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name=:Category1 and n.code2name=:Category2 and n.code3name LIKE %:Category3%") // 4. 대분류, 중분류, 소분류 선택
	List<Object[]> findCode1nameCode2nameCode3nameFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2, @Param("Category3") String Category3);

//	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name LIKE %:Category1% and n.code2name LIKE %:Category2% and n.code3name LIKE %:Category3%") // 4. 대분류, 중분류, 소분류 선택
//	List<Object[]> findCode1nameCode2nameCode3nameFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2, @Param("Category3") String Category3);

	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name=:Category1 and n.code2name=:Category2 and n.code3name=:Category3 and n.foodname LIKE %:Keyword%") // 5. 대분류, 중분류, 소분류 O + Keyword 입력
	List<Object[]> findCode1nameCode2nameCode3nameKeywordFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2, @Param("Category3") String Category3, @Param("Keyword") String Keyword);

//	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name LIKE %:Category1% and n.code2name LIKE %:Category2% and n.code3name LIKE %:Category3% and n.foodname LIKE %:Keyword%") // 5. 대분류, 중분류, 소분류 O + Keyword 입력
//	List<Object[]> findCode1nameCode2nameCode3nameKeywordFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2, @Param("Category3") String Category3, @Param("Keyword") String Keyword);
	
	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name=:Category1 and n.code2name=:Category2") // 6. 대분류, 중분류 선택
	List<Object[]> findCode1nameCode2nameFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2);
	
	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name=:Category1 and n.code2name=:Category2 and n.foodname LIKE %:Keyword%") // 7. 대분류, 중분류만 선택 + Keyword 입력
	List<Object[]> findCode1nameCode2nameKeywordFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2, @Param("Keyword") String Keyword);
	
//	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.code1name LIKE %:Category1% and n.code2name LIKE %:Category2% and n.foodname LIKE %:Keyword%") // 7. 대분류, 중분류만 선택 + Keyword 입력
//	List<Object[]> findCode1nameCode2nameFoodname(@Param("Category1") String Category1, @Param("Category2") String Category2, @Param("Keyword") String Keyword);

	@Query("SELECT n.id, n.code1name, n.code2name, n.code3name, n.originname, n.foodname FROM Nutrition n WHERE n.foodname LIKE %:Keyword%") // 8. 키워드 입력
	List<Object[]> findKeywordFoodname(@Param("Keyword") String Keyword);
}
