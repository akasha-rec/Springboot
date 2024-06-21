package edu.pnu.service;

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
			idset.setRid(detailResult.getNum());
			idset.setNid(item);
			idsetRepo.save(idset);
		}
		
	}
}
