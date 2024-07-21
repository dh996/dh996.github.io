package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
@Table(name = "simul_process_t")
public class SimulProcessT {

	@ManyToOne
	@JoinColumn(name = "simul_sid", referencedColumnName = "simul_sid", foreignKey = @ForeignKey(name = "fk_list_pt"))
	private String simul_sid;
	
	@Embedded
	ProcessEmbedded processEmbedded;
	
	@Column(name = "kill")
	private int kill;
	
	@Column(name = "tower")
	private int tower;
	
	@Column(name = "champ1")
	private int champ1;
	
	@Column(name = "champ2")
	private int champ2;
	
	@Column(name = "champ3")
	private int champ3;
	
	@Column(name = "champ4")
	private int champ4;
	
	@Column(name = "champ5")
	private int champ5;
	
	@Column(name = "top1")
	private int top1;
	
	@Column(name = "top2")
	private int top2;
	
	@Column(name = "top3")
	private int top3;
	
	@Column(name = "topi")
	private int topi;
	
	@Column(name = "mid1")
	private int mid1;
	
	@Column(name = "mid2")
	private int mid2;
	
	@Column(name = "mid3")
	private int mid3;
	
	@Column(name = "midi")
	private int midi;
	
	@Column(name = "bot1")
	private int bot1;
	
	@Column(name = "bot2")
	private int bot2;
	
	@Column(name = "bot3")
	private int bot3;
	
	@Column(name = "boti")
	private int boti;
	
	@Column(name = "leftt")
	private int leftt;
	
	@Column(name = "rightt")
	private int rightt;
	
	@Column(name = "nexus")
	private int nexus;
	
	@Column(name = "baron")
	private int baron;
	
	@Column(name = "elder")
	private int elder;
	
	public SimulProcessT(RecordSimulProcess recordProcess, ProcessEmbedded processEmbedded) {
		// TODO Auto-generated constructor stub
		this.simul_sid = recordProcess.getSid();
		this.processEmbedded = processEmbedded;
		if(processEmbedded.getSimul_team() == 0) {
			this.kill = recordProcess.getKillU();
			this.tower = recordProcess.getTowerU();
			this.champ1 = recordProcess.getC1u();
			this.champ2 = recordProcess.getC2u();
			this.champ3 = recordProcess.getC3u();
			this.champ4 = recordProcess.getC4u();
			this.champ5 = recordProcess.getC5u();
			this.top1 = recordProcess.getT1U();
			this.top2 = recordProcess.getT2U();
			this.top3 = recordProcess.getT3U();
			this.topi = recordProcess.getTiU();
			this.mid1 = recordProcess.getM1U();
			this.mid2 = recordProcess.getM2U();
			this.mid3 = recordProcess.getM3U();
			this.midi = recordProcess.getMiU();
			this.bot1 = recordProcess.getB1U();
			this.bot2 = recordProcess.getB2U();
			this.bot3 = recordProcess.getB3U();
			this.boti = recordProcess.getBiU();
			this.leftt = recordProcess.getLtU();
			this.rightt = recordProcess.getRtU();
			this.nexus = recordProcess.getNexusU();
			this.baron = recordProcess.getBaronU();
			this.elder = recordProcess.getElderU();
		}else if(processEmbedded.getSimul_team() == 1) {
			this.kill =recordProcess.getKillE();
			this.tower = recordProcess.getTowerE();
			this.champ1 = recordProcess.getC1e();
			this.champ2 = recordProcess.getC2e();
			this.champ3 = recordProcess.getC3e();
			this.champ4 = recordProcess.getC4e();
			this.champ5 = recordProcess.getC5e();
			this.top1 = recordProcess.getT1E();
			this.top2 = recordProcess.getT2E();
			this.top3 = recordProcess.getT3E();
			this.topi = recordProcess.getTiE();
			this.mid1 = recordProcess.getM1E();
			this.mid2 = recordProcess.getM2E();
			this.mid3 = recordProcess.getM3E();
			this.midi = recordProcess.getMiE();
			this.bot1 = recordProcess.getB1E();
			this.bot2 = recordProcess.getB2E();
			this.bot3 = recordProcess.getB3E();
			this.boti = recordProcess.getBiE();
			this.leftt = recordProcess.getLtE();
			this.rightt = recordProcess.getRtE();
			this.nexus = recordProcess.getNexusE();
			this.baron = recordProcess.getBaronE();
			this.elder = recordProcess.getElderE();
		}
	}
}
