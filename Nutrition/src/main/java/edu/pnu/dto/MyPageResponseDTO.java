package edu.pnu.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponseDTO {
	private int rid;
	private String title;
	private String age;
	private String condition1;
	private String condition2;
	
	private List<String> selectedItems;
	// +) private Double percentage(total / required * 100) > ResultSaveService에서 NutriResponseDTO로 변환하는 과정에서 계산식으로 저장
	private List<NutriResponseDTO> nutriTotal;
}
