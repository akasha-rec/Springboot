package edu.pnu.service;
// 가장 먼저 저장되서 부모 테이블격으로 rid 생성 > 자식 테이블격의 setRid
//한 페이지의 데이터를 3개의 테이블로 저장할 때 먼저 저장되고 순차적으로 저장하는 경우는 처음이라 서비스 클래스 3개로 나누는 시도로 시작 > 해도 되나 하나의 서비스 클래스에서 각각의 Repository로 각각 Save 해주면 됨

import org.springframework.stereotype.Service;

import edu.pnu.domain.SelectResult;
import edu.pnu.dto.DetailResultDTO;
import edu.pnu.persistence.SelectResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SelectResultService {
	
	private final SelectResultRepository selectResultRepo;
	
	@Transactional
	public void saveSelectResult(DetailResultDTO detailResult) {
		
		SelectResult selectResult = new SelectResult();
//		selectResult.setRid(detailResult.getNum());
		selectResult.setTitle(detailResult.getTitle());
		selectResult.setUserId(detailResult.getUserId());
		selectResult.setAge(detailResult.getAge());
		selectResult.setCondition1(detailResult.getCondition1());
		selectResult.setCondition2(detailResult.getCondition2());
//		selectResult.setResultdate(detailResult.getTimeStamp());
		
		selectResultRepo.save(selectResult);
	}
}
