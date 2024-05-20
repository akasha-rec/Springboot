package edu.pnu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //이 클래스는 데이터베이스와 매핑되는 클래스라고 JPA에게 알려주는 어노테이션 - 와 함께 하는 ~Repository를 persistence에 인터페이스로 생성
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	@ToString.Exclude //오버플로우 방지하기 위해
	@Builder.Default
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER, cascade = CascadeType.ALL) //Member member라는 필드와 매핑될 거라고 알려주는 것
	private List<Board> boardList = new ArrayList<>();
}