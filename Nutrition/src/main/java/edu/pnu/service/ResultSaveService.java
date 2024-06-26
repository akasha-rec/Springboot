package edu.pnu.service; // 테이블 순차적으로 저장하기 최종

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.IdSet;
import edu.pnu.domain.NutriResult;
import edu.pnu.domain.SelectResult;
import edu.pnu.dto.DetailResultDTO;
import edu.pnu.dto.NutriTotalDTO;
import edu.pnu.persistence.IdSetRepository;
import edu.pnu.persistence.NutriResultRepository;
import edu.pnu.persistence.SelectResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResultSaveService {
	
	private final SelectResultRepository selectRepo;
	private final IdSetRepository idSetRepo;
	private final NutriResultRepository nutriRepo;
	
	@Transactional
	public void resultSave(DetailResultDTO detailDTO) {
		
		SelectResult select = new SelectResult();
		select.setTitle(detailDTO.getTitle());
		select.setUser_id(detailDTO.getUserId());
		select.setAge(detailDTO.getAge());
		select.setCondition1(detailDTO.getCondition1());
		select.setCondition2(detailDTO.getCondition2());
		
		select = selectRepo.save(select);
		
		List<Integer> selectedItems = detailDTO.getSelectedItems();
		for (Integer item : selectedItems) {
			IdSet idset = new IdSet();
			idset.setRid(select.getRid());
			idset.setNid(item);
			
			idSetRepo.save(idset);
		}
		
		List<NutriTotalDTO> nResult = detailDTO.getNutriTotal();
		
		for (NutriTotalDTO nutriDTO : nResult) {
			NutriResult nutri = new NutriResult();
			nutri.setRid(select.getRid());
			nutri.setName(nutriDTO.getName());
			nutri.setRequired(nutriDTO.getRequired());
			nutri.setTotal(nutriDTO.getTotal());
			
			nutriRepo.save(nutri);
		}
	}
}
