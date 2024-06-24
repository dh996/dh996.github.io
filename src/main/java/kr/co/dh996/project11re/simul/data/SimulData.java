package kr.co.dh996.project11re.simul.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulData {

	private String champName;
	private int team; //0이라면 아군 1이라면 적군입니다.
	private int kill;
	private int death;
	private int assist;
	private int level;
	private int num; //몇 번째 챔피언인지 표기하기위한 안전장치입니다.
}
