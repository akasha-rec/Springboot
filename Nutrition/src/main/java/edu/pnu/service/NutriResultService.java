package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.NutriResult;
import edu.pnu.dto.DetailResultDTO;
import edu.pnu.dto.NutriTotalDTO;
import edu.pnu.persistence.NutriResultRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NutriResultService {
	
	private final NutriResultRepository nutriRepo;
	
	public void saveNutriResult(DetailResultDTO detailResult) {
				
		List<NutriTotalDTO> nutriTotal = detailResult.getNutriTotal();
		
		for (NutriTotalDTO nutriDTO : nutriTotal) {
			
			NutriResult nutriResult = new NutriResult();
			
			nutriResult.setRid(detailResult.getNum());
			nutriResult.setName(nutriDTO.getName());
			nutriResult.setRequired(nutriDTO.getRequired());
			nutriResult.setTotal(nutriDTO.getTotal());
			nutriRepo.save(nutriResult);
		}	
	}
}
