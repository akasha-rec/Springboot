package edu.pnu.service; // 테이블 순차적으로 저장하기 최종

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.IdSet;
import edu.pnu.domain.MyMemo;
import edu.pnu.domain.NutriResult;
import edu.pnu.domain.SelectResult;
import edu.pnu.dto.DetailResultDTO;
import edu.pnu.dto.MyMemoDTO;
import edu.pnu.dto.MyPageDTO;
import edu.pnu.dto.MyPageResponseDTO;
import edu.pnu.dto.NutriResponseDTO;
import edu.pnu.dto.NutriTotalDTO;
import edu.pnu.persistence.IdSetRepository;
import edu.pnu.persistence.MyMemoRepository;
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
	private final MyMemoRepository memoRepo;
	
	@Transactional
	public void resultSave(DetailResultDTO detailDTO) {
		
		SelectResult select = new SelectResult();
		select.setTitle(detailDTO.getTitle());
		select.setUserId(detailDTO.getUserId());
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
	
	public List<MyPageDTO> sendMyPage(String userId) { // MyPage 영양 파트 게시글
		List<SelectResult> sr = selectRepo.findByUserId(userId); // userId를 통해 찾은 결과인 Entity 클래스
		List<MyPageDTO> lst = new ArrayList<>();
		for (SelectResult result : sr) { // front에게 전달하기 위해 반복문을 돌며 DTO 클래스로 변환
			MyPageDTO dto = new MyPageDTO();
			dto.setRid(result.getRid());
			dto.setTitle(result.getTitle());
			dto.setResultdate(result.getResultdate());
			lst.add(dto);
		}
		return lst;
	}
	
	public List<MyMemoDTO> memoList(String userId) {
		List<MyMemo> mm = memoRepo.findByMemberUserId(userId);
		List<MyMemoDTO> list = new ArrayList<>();
		for (MyMemo m : mm) {
			MyMemoDTO dto1 = new MyMemoDTO();
			dto1.setSeq(m.getSeq());
			dto1.setTitle(m.getTitle());
			dto1.setPostdate(m.getPostdate());
			list.add(dto1);
		}
		return list;	
	}
	
	public MyPageResponseDTO sendMyPageByRid(Integer rid) {
		SelectResult sr = selectRepo.findById(rid).get();
		MyPageResponseDTO mprDTO = MyPageResponseDTO.builder()
				.rid(sr.getRid())
				.title(sr.getTitle())
				.age(sr.getAge())
				.condition1(sr.getCondition1())
				.condition2(sr.getCondition2()).build();
		
		// IdSet
		List<Object[]> fdList = idSetRepo.getFoodname(rid);
		List<String> selectedItems = new ArrayList<>();
		for (Object[] result : fdList) {
			String item = (String) result[0];
			selectedItems.add(item);			
		}
		mprDTO.setSelectedItems(selectedItems);
		
		// nutriResult
		List<NutriResult> nr = nutriRepo.findByRid(rid);
		List<NutriResponseDTO> lst = new ArrayList<>();
		for (NutriResult result : nr) {
			NutriResponseDTO dto = new NutriResponseDTO();
			dto.setName(result.getName());
			dto.setRequired(result.getRequired());
			dto.setTotal(result.getTotal());
//			dto.setPercentage(Math.round(result.getTotal() / result.getRequired())* 100);
			dto.setPercentage(Math.round(result.getTotal() / result.getRequired() * 1000) / 10.0);
			lst.add(dto);
		}
		mprDTO.setNutriTotal(lst);
		
		return mprDTO;
	}
}
