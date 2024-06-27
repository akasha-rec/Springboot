package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.IdSet;
import edu.pnu.domain.NutriResult;
import edu.pnu.domain.SelectResult;
import edu.pnu.persistence.IdSetRepository;
import edu.pnu.persistence.NutriResultRepository;
import edu.pnu.persistence.SelectResultRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
public class DataSave {
	
	@Autowired
	private SelectResultRepository selectRepo;
	
	@Autowired
	private IdSetRepository IdRepo;
	
	@Autowired
	private NutriResultRepository nutriRepo;
	
	@Transactional
	@Test
	public void insertSelect() {
		
		SelectResult select = new SelectResult(); // 엔티티 객체 생성
//		select.setRid(4);
		select.setTitle("저장 이해");
		select.setUserId("testSave@naver.com");
		select.setAge("19~29");
		select.setCondition1("임신 1분기(~12주)");
		select.setCondition2("8주");
		
		selectRepo.save(select);

		IdSet id = new IdSet();
		id.setRid(select.getRid());
		id.setNid(20);
		IdRepo.save(id);
		
		NutriResult nr = new NutriResult();
			
		nr.setRid(select.getRid());
		nr.setName("test");
		nr.setRequired(1.0);
		nr.setTotal(0.4);
		nutriRepo.save(nr);
		}
		}
