package edu.pnu.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailResultDTO {
	private int num;
	private String title;
	private String age;
	private String condition1;
	private String condition2;
//	private LocalDateTime timeStamp;
	
	private List<Integer> selectedItems;
	private List<NutriTotalDTO> nutriTotal;
}
