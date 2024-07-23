package kr.co.dh996.project11re.simul.machin.round.extendPT;

import org.springframework.stereotype.Component;

@Component
class UtilSupport extends SupportParent{

    UtilSupport(){
		super();
		setDefaultHp(550);
		setDefaultSp(80);
    }
}
