package edu.pnu.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailResultDTO { // 사용자가 선택한 자신의 상태값, 음식명과 그에 따른 각 영양소별 합산 결과를 모두 받아오는 DTO
//	private int num; //JPA가 자동으로 생성해줌
	private String title;
	private String age;
	private String condition1;
	private String condition2;
	private String userId;
	
	private List<Integer> selectedItems;
	private List<NutriTotalDTO> nutriTotal;
}
