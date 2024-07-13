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
@Table(name = "simul_data")
public class SimulData {

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_data"))
	private String simul_sid;

	@Column(name = "simul_champ")
	private String simul_champ;
	
	@Column(name = "simul_team")
	private int simul_team;
	
	@Column(name = "simul_num")
	private int simul_num;
	
	@Column(name = "simul_level")
	private int simul_level;
	
	@Column(name = "simul_kill")
	private int simul_kill;
	
	@Column(name = "simul_death")
	private int simul_death;
	
	@Column(name = "simul_assist")
	private int simul_assist;
}
