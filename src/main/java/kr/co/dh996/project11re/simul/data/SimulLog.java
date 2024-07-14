package kr.co.dh996.project11re.simul.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimulLog {

	private String sid;
	private String message;
	private int round;
	private int team;
	private int num;
	
	//생성자 타입 0. 기본 정보를 저장하는 생성자
	public SimulLog(String sid, int round, int num, int team) {
		this.sid = sid;
		this.round = round;
		this.num = num;
		this.team = team;
	}

	//생성자 타입 0. 입력 스트링이 0개인 경우
	public SimulLog(String sid, int round, int num, String type, int team) {
		// TODO Auto-generated constructor stub
		this(sid, round, num, team);
		if("init".equals(type)) {
	    	if(team == 0) {
	    		this.message = "아군의 선제공격입니다. 적군의 공격 횟수가 감소합니다.";
	    	}else {
	    		this.message = "적군의 선제공격입니다. 아군의 공격 횟수가 감소합니다.";
	    	}
	    }else if("dive".equals(type)) {
	    	if(team == 0) {
	    		this.message = "아군이 포탑을 끼고 싸웁니다. 적군의 체력이 감소합니다.";
	    	}else {
	    		this.message = "적군이 포탑을 끼고 싸웁니다. 아군의 공격 횟수가 감소합니다.";
	    	}
	    }
	}
	
	//생성자 타입 1. 입력 스트링이 1개인 경우
	public SimulLog(String sid, int round, int num, String message, String type, int team) {
		// TODO Auto-generated constructor stub
		this(sid, round, num, team);
		if("field".equals(type)) {
			this.message = round+"번째 전장은 "+message+"입니다.";
		}else if("death".equals(type)) {
			if(team == 0) {
				this.message = "적군 팀의 "+message+"이/가 사망하였습니다.";
			}else {
				this.message = "아군 팀의 "+message+"이/가 사망하였습니다.";
			}
		}else if("hunt".equals(type)) {
			if(team == 0) {
				this.message = "아군 팀이 "+message+"을/를 사냥하였습니다.";
			}else {
				this.message = "적군 팀이 "+message+"을/를 사냥하였습니다.";
			}
		}else if("destroy".equals(type)) {
			if(team == 0) {
				this.message = "아군 팀이 "+message+"을/를 파괴하였습니다.";
			}else {
				this.message = "적군 팀이 "+message+"을/를 파괴하였습니다.";
			}
		}else if("spawn".equals(type)) {
			this.message = message+"이/가 소환되었습니다.";
		}
	}
	
	//생성자 타입 2. 입력 스트링이 2개인 경우
	public SimulLog(String sid, int round, int num, String message1, String message2,
			String type, int team) {
		// TODO Auto-generated constructor stub
		this(sid, round, num, team);
		if("level".equals(type)) {
			if(team == 0) {
				this.message = "아군 팀의 "+message1+"이/가 "+message2+"레벨로 성장하였습니다.";
			}else {
				this.message = "적군 팀의 "+message1+"이/가 "+message2+"레벨로 성장하였습니다.";
			}
		}else if("siege".equals(type)) {
			if(team == 0) {
				this.message = "아군 팀이 적군의 "+message1+"에게 "+message2+"의 피해를 입혔습니다.";
			}else {
				this.message = "적군 팀이 아군의 "+message1+"에게 "+message2+"의 피해를 입혔습니다.";
			}
		}
	}

	//생성자 타입 3. 입력 스트링이 3개인 경우
	public SimulLog(String sid, int round, int num, String message1, String message2, String message3,
			String type, int team) {
		// TODO Auto-generated constructor stub
		this(sid, round, num, team);
		if("attack".equals(type)) {
			if(team == 0) {
				this.message = "아군 팀의 "+message1+"이/가 적군 팀의 "+message2+"을/를 공격해 "+message3+"의 대미지를 주었습니다.";
			}else {
				this.message = "적군 팀의 "+message1+"이/가 아군 팀의 "+message2+"을/를 공격해 "+message3+"의 대미지를 주었습니다.";
			}
		}
	}
}
