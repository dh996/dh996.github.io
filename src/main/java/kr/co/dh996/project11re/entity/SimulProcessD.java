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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "simul_process_d")
public class SimulProcessD {
	
	@Id
    @Column(name = "pk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pd"))
	private SimulList simulSid;
	
	@Column(name = "dragon")
	private String dragon;
	
	@Embedded
	ProcessEmbedded processEmbedded;

	public SimulProcessD(SimulList simulList, String dragon, ProcessEmbedded processEmbedded) {
		// TODO Auto-generated constructor stub
		this.simulSid = simulList;
		this.dragon = dragon;
		this.processEmbedded = processEmbedded;
	}
}
