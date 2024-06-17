package edu.pnu.domain;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NutriRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	private String age;
	private String gubun;
	private String weekNum;
	
	private int energy;
	private double e_percent;
	private double carbohydrate;
	private double c_percent;
	private double dietaryFiber;
	private double d_percent;
	private double fat;
	private double f_percent;
	private double linoleic;
	private double li_percent;
	private double protein;
	private double pro_percent;
	private double methionine;
	private double meth_percent;
	private double leucine;
	private double leu_percent;
	private double isoleucine;
	private double iso_percent;
	private double valine;
	private double v_percent;
	private double lysine;
	private double ly_percent;
	private double threonine;
	private double thre_percent;
	private double histidine;
	private double his_percent;
	private double water;
	private double water_percent;
	private int vitA;
	private double vitA_percent;
	private int vitD;
	private double vitD_percent;
	private double vitC;
	private double vitC_percent;
	private double thiamine;
	private double thia_percent;
	private double riboflavin;
	private double ribo_percent;
	private double niacin;
	private double nia_percent;
	private double folate;
	private double fola_percent;
	private double vitB12;
	private double vitB12_percent;
	private int P;
	private double p_percent;
	private int K;
	private double k_percent;
	private double Fe;
	private double fe_percent;
	private double Zn;
	private double zn_percent;
	private int Cu;
	private double cu_percent;
	private double Mn;
	private double mn_percent;
	private double Se;
	private double se_percent;
	private int Ca;
	private double ca_percent;
	private int Na;
	private double na_percent;
	private double Mg;
	private double mg_percent;
	private double ALA;
	private double ALA_percent;
}
