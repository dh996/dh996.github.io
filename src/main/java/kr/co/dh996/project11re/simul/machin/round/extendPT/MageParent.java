package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

import kr.co.dh996.project11re.simul.machin.abs.PowerTable;

@Component
class MageParent extends PowerTable{

	MageParent(){
		super();
		setDefaultHp(550);
		setDefaultAp(130);
		setDefaultSp(160);
		setDefaultIp(60);
		setDefaultAs(3);
	}
}
