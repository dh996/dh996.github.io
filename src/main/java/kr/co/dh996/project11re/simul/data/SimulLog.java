package kr.co.dh996.project11re.simul.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	//생성자 타입 1. 입력 스트링이 1개인 경우
	public SimulLog(String sid, int round, int num, String message, String type, int team) {
		// TODO Auto-generated constructor stub
		this(sid, round, num, team);
		if(type.equals("field")) {
			this.message = round+"번째 전장은"+message+"입니다.";
		}
	}
	
	//생성자 타입 2. 입력 스트링이 2개인 경우
	public SimulLog(String sid, int round, int num, String message1, String message2, String type, int team) {
		// TODO Auto-generated constructor stub
		this(sid, round, num, team);
		if(type.equals("level")) {
			if(team == 0) {
				this.message = "아군 팀의"+message1+"이/가"+message2+"레벨로 성장하였습니다.";
			}else {
				this.message = "적군 팀의"+message1+"이/가"+message2+"레벨로 성장하였습니다.";
			}
		}
	}
}
