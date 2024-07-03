package kr.co.dh996.project11re.simul.machin.round.extendPT;

import kr.co.dh996.project11re.simul.data.BattlePower;
import kr.co.dh996.project11re.simul.machin.abs.PowerTable;

class ADParent extends PowerTable{
	
	ADParent(){
		super();
		setDefaultIp(20);
		setDefaultAg(30);
		setDefaultAs(8);
	}

	protected void checkUtilSup(BattlePower battlePower) {
		setPower(battlePower,
				battlePower.getHp()*(1.1),
				battlePower.getAttackPower()*(1.1),
				battlePower.getSiegePower(),
				battlePower.getInitPower(),
				battlePower.getAggroGauge()
		);
	}
}
