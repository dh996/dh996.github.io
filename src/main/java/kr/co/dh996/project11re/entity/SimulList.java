package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	private String simulSid;
	
	@Column(name = "simul_version")
	private String simulVersion;
	
	@Column(name = "simul_player")
	private String simulPlayer;
	
	@Column(name = "simul_winlose")
	private int simulWinlose;

	public SimulList(SimulMainObject simulMO) {
		// TODO Auto-generated constructor stub
		this.simulSid = simulMO.getSid();
		this.simulPlayer = simulMO.getUserName();
		this.simulVersion = simulMO.getVersion();
		this.simulWinlose = simulMO.getWinLose();
	}
}
