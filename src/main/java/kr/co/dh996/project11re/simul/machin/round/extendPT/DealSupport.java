package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class DealSupport extends SupportParent{

	DealSupport(){
		super();
		setDefaultHp(650);
		setDefaultAp(100);
		setDefaultSp(100);
		setDefaultAs(4);
	}
}
