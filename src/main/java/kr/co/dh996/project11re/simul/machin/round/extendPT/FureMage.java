package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class FureMage extends MageParent{

	FureMage() {
		super();
		setDefaultHp(500);
		setDefaultAp(150);
		setDefaultSp(170);
		setDefaultIp(50);
	}
}
