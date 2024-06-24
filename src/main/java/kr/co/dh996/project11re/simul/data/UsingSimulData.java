package kr.co.dh996.project11re.simul.data;

import kr.co.dh996.project11re.dto.champDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsingSimulData extends SimulData {
	//시뮬레이션 기능에만 사용되는 변수들이 추가됩니다.

	private String champID;
	private String position;
	private int exp;
	private BattlePower battlePower;
	
	public UsingSimulData(champDTO champDTO) {
		// TODO Auto-generated constructor stub
		//champDTO의 내용이 확정되면 작성예정입니다.
	}
}
