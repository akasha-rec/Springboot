package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor //멤버변수를 가지는 생성자 생성
@NoArgsConstructor //기본 생성자를 생성해주는 어노테이션
@Builder
public class MemberVO {
	private int id;
	private String pass;
	private String name;
	private Date regidate;
	
}
