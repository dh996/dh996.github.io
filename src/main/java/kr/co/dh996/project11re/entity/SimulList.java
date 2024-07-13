package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "simul_list")
public class SimulList {

	@Id
	@Column(name = "simul_sid")
	private String simul_sid;
	
	@Column(name = "simul_version")
	private String simul_version;
	
	@Column(name = "simul_player")
	private String simul_player;
	
	@Column(name = "simul_winlose")
	private int simul_winlose;
}
