package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class ADSupport extends SupportParent{

	ADSupport() {
		super();
		setDefaultHp(450);
		setDefaultAp(100);
		setDefaultSp(120);
		setDefaultIp(30);
		setDefaultAs(5);
	}
}
