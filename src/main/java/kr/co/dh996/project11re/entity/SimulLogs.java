package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import kr.co.dh996.project11re.simul.data.SimulLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "simul_logs")
public class SimulLogs {
	
	@Id
    @Column(name = "pk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_logs"))
	private SimulList simulSid;
	
	@Column(name = "simul_message")
	private String simulMessage;
	
	@Embedded
	ProcessEmbedded processEmbedded;
	
	@Column(name = "simul_num")
	private int simulNum;

	public SimulLogs(SimulList simulList, SimulLog recordLog, ProcessEmbedded processEmbedded) {
		// TODO Auto-generated constructor stub
		this.simulSid = simulList;
		this.simulMessage = recordLog.getMessage();
		this.processEmbedded = processEmbedded;
		this.simulNum = recordLog.getNum();
	}
}
