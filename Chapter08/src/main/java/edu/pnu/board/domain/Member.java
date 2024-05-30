package edu.pnu.board.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@Entity
public class Member {
	
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
	@JsonIgnore
	@ToString.Exclude // 순환 참조를 해결하기 위해서 변수 제외
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER) // Member가 양방향 관계의 주인이 아님을 설정 + Member가 조회될 때 Board 목록도 같이 조회되도록 즉시 로딩으로 설정
	private List<Board> boardList = new ArrayList<Board>();
}
