package kr.co.dh996.project11re.simul.data;

import java.util.List;

import kr.co.dh996.project11re.dto.ChampDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsingSimulData extends SimulData {
	//시뮬레이션 기능에만 사용되는 변수들이 추가됩니다.

	private String champID;
	private String position;
	private int exp;
	private BattlePower battlePower;
	private List<String> champTags;
	
	public UsingSimulData(ChampDTO champDTO, int num, int team) {
		// TODO Auto-generated constructor stub
		super();
		this.champID = champDTO.getChampID();
		this.position = "none";
		this.exp = 0;
		this.battlePower = new BattlePower();
		this.champTags = champDTO.getChampTags();
		setChampName(champDTO.getChampName());
		setTeam(team);
		setKill(0);
		setDeath(0);
		setAssist(0);
		setLevel(1);
		setNum(num);
	}
}
