package edu.pnu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NutriTotalDTO {
	private String name;
	private Double total;
	private Double required;
}
