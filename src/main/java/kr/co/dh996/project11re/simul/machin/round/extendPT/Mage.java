package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class Mage extends MageParent{

	Mage() {
		super();
		setDefaultHp(550);
		setDefaultAp(130);
		setDefaultSp(160);
		setDefaultIp(60);
	}
}
