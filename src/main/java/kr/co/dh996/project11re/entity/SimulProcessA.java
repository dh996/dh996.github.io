package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.co.dh996.project11re.simul.data.RecordSimulProcess;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "simul_process_a")
public class SimulProcessA {

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pa"))
	private String simul_sid;
	
	@Column(name = "field")
	private String field;
	
	@Column(name = "dragonnest")
	private String dragonnest;
	
	@Column(name = "baronnest")
	private int baronnest;
	
	@Column(name = "simul_round")
	private int simul_round;

	public SimulProcessA(RecordSimulProcess recordProcess) {
		// TODO Auto-generated constructor stub
		this.simul_sid = recordProcess.getSid();
		this.field = recordProcess.getField();
		this.dragonnest = recordProcess.getDragonNest();
		this.baronnest = recordProcess.getBaronNest();
		this.simul_round = recordProcess.getRound();
	}
}
