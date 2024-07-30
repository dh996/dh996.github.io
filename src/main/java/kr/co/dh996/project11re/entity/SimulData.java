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

import kr.co.dh996.project11re.simul.data.RecordSimulData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "simul_data")
public class SimulData {
	
	@Id
    @Column(name = "pk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_data"))
	private SimulList simulSid;

	@Column(name = "simul_champ")
	private String simulChamp;
	
	@Column(name = "simul_team")
	private int simulTeam;
	
	@Column(name = "simul_num")
	private int simulNum;
	
	@Column(name = "simul_level")
	private int simulLevel;
	
	@Column(name = "simul_kill")
	private int simulKill;
	
	@Column(name = "simul_death")
	private int simulDeath;
	
	@Column(name = "simul_assist")
	private int simulAssist;

	public SimulData(SimulList simulList, RecordSimulData recordData) {
		// TODO Auto-generated constructor stub
		this.simulSid = simulList;
        this.simulChamp = recordData.getChampName();
        this.simulTeam = recordData.getTeam();
        this.simulNum = recordData.getNum();
        this.simulLevel = recordData.getLevel();
        this.simulKill = recordData.getKill();
        this.simulDeath = recordData.getDeath();
        this.simulAssist = recordData.getAssist();
	}
}
