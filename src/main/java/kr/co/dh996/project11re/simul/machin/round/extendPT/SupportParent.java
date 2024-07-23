package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.PowerTable;

@Component
class SupportParent extends PowerTable{

	SupportParent(){
		super();
		setDefaultAp(70);
		setDefaultIp(70);
		setDefaultAg(50);
		setDefaultAs(1);
	}
}
