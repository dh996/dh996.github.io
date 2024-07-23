package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class MageTank extends TankParent{

	MageTank() {
		super();
		setDefaultHp(1000);
		setDefaultAp(180);
		setDefaultSp(150);
		setDefaultIp(110);
	}
}
