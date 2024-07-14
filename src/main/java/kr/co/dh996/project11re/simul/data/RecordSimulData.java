package kr.co.dh996.project11re.simul.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordSimulData extends SimulData{
	//데이터베이스에 저장하기 위한 변수를 추가합니다
	
	private String sid;
	
	public RecordSimulData(UsingSimulData usingSimulData, String sid) {
		// TODO Auto-generated constructor stub
		super();
		this.sid = sid;
		setChampName(usingSimulData.getChampName());
		setTeam(usingSimulData.getTeam());
		setKill(usingSimulData.getKill());
		setDeath(usingSimulData.getDeath());
		setAssist(usingSimulData.getAssist());
		setLevel(usingSimulData.getLevel());
		setNum(usingSimulData.getNum());
	}

}
