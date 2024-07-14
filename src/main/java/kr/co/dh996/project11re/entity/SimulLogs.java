package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
