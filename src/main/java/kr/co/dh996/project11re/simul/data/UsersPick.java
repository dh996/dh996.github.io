package kr.co.dh996.project11re.simul.data;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersPick {
	
	private String userName;
	private List<String> pickChamps;
	private String version;

}
