package kr.co.dh996.project11re.simul.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattlePower {
	//전투 단계에서 사용되는 정보들의 집합입니다.

	private int hp;
	private int attackPower;
	private int initPower;
	private int siegePower;
	private int aggroGauge;
	private int attackSpeed;
	private int roundKill;
	private int roundDeath;
	private int roundAssist;
}
