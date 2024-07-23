package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class FureADC extends ADParent{

	FureADC() {
		super();
		setDefaultHp(400);
		setDefaultAp(150);
		setDefaultSp(200);
	}
}
