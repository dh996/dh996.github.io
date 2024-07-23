package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class FightTank extends TankParent{

	FightTank() {
		super();
		setDefaultHp(1000);
		setDefaultAp(120);
		setDefaultSp(150);
		setDefaultIp(110);
		setDefaultAs(4);
	}
}
