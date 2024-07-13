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
@Table(name = "simul_process_d")
public class SimulProcessD {

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pd"))
	private String simul_sid;
	
	@Column(name = "dragon")
	private String dragon;
	
	@Column(name = "simul_round")
	private int simul_round;
	
	@Column(name = "simul_team")
	private int simul_team;
}
