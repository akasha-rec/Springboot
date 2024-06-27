package edu.pnu.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageDTO {
	
	private int rid;
	private String title;
	private LocalDateTime resultdate;
}
