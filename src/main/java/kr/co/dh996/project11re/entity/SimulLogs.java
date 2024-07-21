package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_logs"))
	private String simul_sid;
	
	@Column(name = "simul_message")
	private String simul_message;
	
	@Embedded
	ProcessEmbedded processEmbedded;
	
	@Column(name = "simul_num")
	private int simul_num;

	public SimulLogs(SimulLog recordLog, ProcessEmbedded processEmbedded) {
		// TODO Auto-generated constructor stub
		this.simul_sid = recordLog.getSid();
		this.simul_message = recordLog.getMessage();
		this.processEmbedded = processEmbedded;
		this.simul_num = recordLog.getNum();
	}
}
