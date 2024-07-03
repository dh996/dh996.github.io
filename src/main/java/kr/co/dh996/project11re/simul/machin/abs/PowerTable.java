package kr.co.dh996.project11re.simul.machin.abs;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.data.UsingSimulData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public abstract class PowerTable {
	//배틀파워 설정 상세기능의 부모 클래스입니다.

	private int defaultHp;
	private int defaultAp;
	private int defaultSp;
	private int defaultIp;
	private int defaultAg;
	private int defaultAs;
	private int scale;
	
	public void setBattlePower(UsingSimulData uSD, int dragonStacks,
			int baron, int elder) {
		setScale(uSD); //전투력 배율을 설정합니다.
		setDefaultPower(uSD.getBattlePower()); //전투력을 설정합니다.
		setPowerByBuff(uSD.getBattlePower(), dragonStacks, baron, elder); //보유 버프에 따른 수치보정을 실행합니다.
	}

	//성장수치 및 누적점수에 따른 전투력 배율을 정합니다.
	protected void setScale(UsingSimulData uSD) {
		// TODO Auto-generated method stub
		this.scale = (30+(uSD.getLevel()*15)+(uSD.getKill()*2)+(uSD.getAssist()/8))/10;
		if(this.scale>=45) {
			this.scale = 45;
		}
	}

	//전투력 배열에 맞춰 전투력을 설정하고 턴별 획득 점수 변수를 초기화합니다.
	protected void setDefaultPower(BattlePower battlePower) {
		// TODO Auto-generated method stub
		setPower(battlePower, (defaultHp*scale)/10, (defaultAp*scale)/15,
				(defaultSp*scale)/30, (defaultIp*scale)/10, (defaultAg*scale)/10);
		battlePower.setAttackSpeed(defaultAs);
		battlePower.setRoundKill(0);
		battlePower.setRoundDeath(0);
		battlePower.setRoundAssist(0);
	}
	
	//전투력 설정을 위한 메서드입니다.
	protected void setPower(BattlePower battlePower, double hp, double ap, double sp, double ip, double ag) {
		battlePower.setHp((int) Math.round(hp));
		battlePower.setAttackPower((int) Math.round(ap));
		battlePower.setSiegePower((int) Math.round(sp));
		battlePower.setInitPower((int) Math.round(ip));
		battlePower.setAggroGauge((int) Math.round(ag));
	}

	//보유 버프에 따른 전투력 보정 시스템을 적용합니다.
	protected void setPowerByBuff(BattlePower battlePower, int dragonStacks, int baron, int elder) {
		// TODO Auto-generated method stub
		if(dragonStacks>0) {
			setPower(battlePower,
					battlePower.getHp()*(1+(dragonStacks*0.03)),
					battlePower.getAttackPower()*(1+(dragonStacks*0.025)),
					battlePower.getSiegePower()*(1+(dragonStacks*0.025)),
					battlePower.getInitPower()*(1+(dragonStacks*0.025)),
					battlePower.getAggroGauge()*(1+(dragonStacks*0.01))
			);
		}
		if(baron>0) {
			setPower(battlePower,
					battlePower.getHp()*(1.2),
					battlePower.getAttackPower()*(1.1),
					battlePower.getSiegePower()*(1.3),
					battlePower.getInitPower()*(1.1),
					battlePower.getAggroGauge()*(1.1)
			);
		}
	    if(elder>0) {
	    	setPower(battlePower,
	    			battlePower.getHp()*(1.1),
	    			battlePower.getAttackPower()*(1.3),
	    			battlePower.getSiegePower(),
	    			battlePower.getInitPower()*(1.1),
	    			battlePower.getAggroGauge()
	    	);
		}
	}
}
