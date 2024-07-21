package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import kr.co.dh996.project11re.simul.data.SimulLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ProcessEmbedded {

	@Column(name = "simul_round")
	private int simul_round;
	
	@Column(name = "simul_team")
	private int simul_team;

	public ProcessEmbedded(int round, int team) {
		// TODO Auto-generated constructor stub
		this.simul_round = round;
		this.simul_team = team;
	}

	public ProcessEmbedded(SimulLog recordLog) {
		// TODO Auto-generated constructor stub
	}
}
