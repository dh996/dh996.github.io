package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class ADC extends ADParent{

	ADC() {
		super();
		setDefaultHp(450);
		setDefaultAp(130);
		setDefaultSp(180);
	}
}
