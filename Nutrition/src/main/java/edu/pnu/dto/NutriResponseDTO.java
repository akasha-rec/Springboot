package edu.pnu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NutriResponseDTO {
	private String name;
	private Double total;
	private Double required;
	private Double percentage;
}
