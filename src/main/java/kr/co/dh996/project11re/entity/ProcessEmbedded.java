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
	private int simulRound;
	
	@Column(name = "simul_team")
	private int simulTeam;

	public ProcessEmbedded(int round, int team) {
		// TODO Auto-generated constructor stub
		this.simulRound = round;
		this.simulTeam = team;
	}

	public ProcessEmbedded(SimulLog recordLog) {
		// TODO Auto-generated constructor stub
		this.simulRound = recordLog.getRound();
		this.simulTeam = recordLog.getTeam();
	}
}
