package edu.pnu.service;

//한 페이지의 데이터를 3개의 테이블로 저장할 때 먼저 저장되고 순차적으로 저장하는 경우는 처음이라 서비스 클래스 3개로 나누는 시도로 시작 > 해도 되나 하나의 서비스 클래스에서 각각의 Repository로 각각 Save 해주면 됨
import java.util.List;

//import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.IdSet;
import edu.pnu.dto.DetailResultDTO;
import edu.pnu.persistence.IdSetRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IdSetService {
	private final IdSetRepository idsetRepo;
	
	public void saveIdset(DetailResultDTO detailResult) {
		
		List<Integer> selectedItems = detailResult.getSelectedItems();
		for (Integer item : selectedItems) {
			IdSet idset = new IdSet();
//			idset.setRid(detailResult.getNum());
			idset.setNid(item);
			idsetRepo.save(idset);
		}
		
	}
}
