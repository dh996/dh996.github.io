package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ProcessEmbedded {

	@Column(name = "simul_round")
	private int simul_round;
	
	@Column(name = "simul_team")
	private int simul_team;
}
