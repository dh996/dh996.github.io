package kr.co.dh996.project11re.simul.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsingSimulProcess extends SimulProcess {

	//시뮬레이션 가동에 필요한 오브젝트 소환 기능을 위한 변수를 추가합니다.
	private int dragonCount;
	private int baronCount;
}
