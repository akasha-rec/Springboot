package edu.pnu.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailResultDTO {
//	private int num; //JPA가 자동으로 생성해줌
	private String title;
	private String age;
	private String condition1;
	private String condition2;
	private String userId;
	
	private List<Integer> selectedItems;
	private List<NutriTotalDTO> nutriTotal;
}
