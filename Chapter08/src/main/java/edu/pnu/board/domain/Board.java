package edu.pnu.board.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;
	
	private String title;
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createDate = new Date();
	
	@Column(updatable = false)
	private Long cnt = 0L;
	
	@ToString.Exclude // 순환 참조 해결을 위해
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable = false) // "MEMBER_ID"를 통해 외래키를 관리하도록 설정 + 즉시 로딩 처리시 내부조인으로 처리하여 성능을 향상시키기 위해서 nullable
	private Member member;
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}
