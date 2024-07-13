package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "simul_process_t")
public class SimulProcessT {

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pt"))
	private String simul_sid;

	@Column(name = "simul_round")
	private int simul_round;
	
	@Column(name = "simul_team")
	private int simul_team;
	
	@Column(name = "kill")
	private int kill;
	
	@Column(name = "tower")
	private int tower;
	
	@Column(name = "champ1")
	private int champ1;
	
	@Column(name = "champ2")
	private int champ2;
	
	@Column(name = "champ3")
	private int champ3;
	
	@Column(name = "champ4")
	private int champ4;
	
	@Column(name = "champ5")
	private int champ5;
	
	@Column(name = "top1")
	private int top1;
	
	@Column(name = "top2")
	private int top2;
	
	@Column(name = "top3")
	private int top3;
	
	@Column(name = "topi")
	private int topi;
	
	@Column(name = "mid1")
	private int mid1;
	
	@Column(name = "mid2")
	private int mid2;
	
	@Column(name = "mid3")
	private int mid3;
	
	@Column(name = "midi")
	private int midi;
	
	@Column(name = "bot1")
	private int bot1;
	
	@Column(name = "bot2")
	private int bot2;
	
	@Column(name = "bot3")
	private int bot3;
	
	@Column(name = "boti")
	private int boti;
	
	@Column(name = "leftt")
	private int leftt;
	
	@Column(name = "rightt")
	private int rightt;
	
	@Column(name = "nexus")
	private int nexus;
	
	@Column(name = "baron")
	private int baron;
	
	@Column(name = "elder")
	private int elder;
}
