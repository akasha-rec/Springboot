package edu.pnu.domain; // DB 테이블 컬럼과 동일한 필드를 가진 클래스(DB 처리용 클래스) @Entity : DB에 저장되는 데이터 객체, 직접적으로 연결

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nutrition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // sequence number
	private String foodcode;
	private String foodname; // 식품명
	private String datacode;
	private String gubun;
	private int origincode;
	private String originname; // 식품기원명 (O : 식사유형)
	private String code1;
	private String code1name; // 식품대분류명(O : 대분류명)
	private String code2;
	private String code2name; // 대표식품명(O : 중분류명)
	private String code3;
	private String code3name; // 식품중분류명(O : 소분류명)
	private String code4;
	private String code4name;
	private String code5;
	private String code5name;
	private String amount;
	private int energy;
	private double carbohydrate;
	private double dietaryFiber;
	private double fat;
	private double linoleic;
	private double ALA;
	private double protein;
	private double methionine;
	private double leucine;
	private double isoleucine;
	private double valine;
	private double lysine;
	private double threonine;
	private double histidine;
	private double water;
	private int vitA;
	private int vitD;
	private double vitC;
	private double thiamine;
	private double riboflavin;
	private double niacin;
	private double folate;
	private double vitB12;
	private int Ca;
	private int P;
	private int Na;
	private int K;
	private double Mg;
	private double Fe;
	private double Zn;
	private int Cu;
	private double Mn;
	private double Se;
}
