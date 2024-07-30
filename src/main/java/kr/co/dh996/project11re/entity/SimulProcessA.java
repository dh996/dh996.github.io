package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import kr.co.dh996.project11re.simul.data.RecordSimulProcess;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "simul_process_a")
public class SimulProcessA {
	
	@Id
    @Column(name = "pk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pa"))
	private SimulList simulSid;
	
	@Column(name = "field")
	private String field;
	
	@Column(name = "dragonnest")
	private String dragonnest;
	
	@Column(name = "baronnest")
	private int baronnest;
	
	@Column(name = "simul_round")
	private int simulRound;

	public SimulProcessA(SimulList simulList, RecordSimulProcess recordProcess) {
		// TODO Auto-generated constructor stub
		this.simulSid = simulList;
		this.field = recordProcess.getField();
		this.dragonnest = recordProcess.getDragonNest();
		this.baronnest = recordProcess.getBaronNest();
		this.simulRound = recordProcess.getRound();
	}
}
