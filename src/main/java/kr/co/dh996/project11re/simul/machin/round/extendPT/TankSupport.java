package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class TankSupport extends SupportParent{

	TankSupport(){
		super();
		setDefaultHp(800);
		setDefaultAp(50);
		setDefaultSp(80);
		setDefaultIp(100);
		setDefaultAg(80);
	}
}
