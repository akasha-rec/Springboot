package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //이 클래스는 데이터베이스와 매핑되는 클래스라고 JPA에게 알려주는 어노테이션
public class Board {
	@Id //primary key라고 알려주는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성  : auto_increment와 같은 기능(MySQL, H2)
	private Long seq;
	private String title;
//	private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)//Date 타입 지정해서 넣어주기 위한 코드
	private Date createDate;
	private Long cnt;
	
	@ManyToOne //Board는 N의 관계라는 뜻
	@JoinColumn(name="MEMBER_ID") //지정한 이름으로 board 테이블에 외래키 생성
	private Member member;
}
