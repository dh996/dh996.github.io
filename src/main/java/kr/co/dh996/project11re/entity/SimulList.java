package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.dh996.project11re.simul.data.SimulMainObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

	public SimulList(SimulMainObject simulMO) {
		// TODO Auto-generated constructor stub
		this.simul_sid = simulMO.getSid();
		this.simul_player = simulMO.getUserName();
		this.simul_version = simulMO.getVersion();
		this.simul_winlose = simulMO.getWinLose();
	}
}
