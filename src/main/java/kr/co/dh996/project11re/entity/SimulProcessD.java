package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "simul_process_d")
public class SimulProcessD {

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pd"))
	private String simul_sid;
	
	@Column(name = "dragon")
	private String dragon;
	
	@Embedded
	ProcessEmbedded processEmbedded;

	public SimulProcessD(String sid, String dragon, ProcessEmbedded processEmbedded) {
		// TODO Auto-generated constructor stub
		this.simul_sid = sid;
		this.dragon = dragon;
		this.processEmbedded = processEmbedded;
	}
}
