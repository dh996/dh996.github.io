package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class Support extends SupportParent{

	Support(){
		super();
		setDefaultHp(600);
		setDefaultSp(80);
	}
}
