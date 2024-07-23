package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class FureTank extends TankParent{

	FureTank() {
		super();
		setDefaultHp(1300);
		setDefaultAp(70);
		setDefaultSp(100);
		setDefaultIp(130);
	}
}
