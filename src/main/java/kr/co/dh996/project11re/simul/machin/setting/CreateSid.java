package kr.co.dh996.project11re.simul.machin.setting;

import java.util.UUID;

public class CreateSid {

	public static String createSid() {
		return "SID_"+UUID.randomUUID().toString();
	}
}
