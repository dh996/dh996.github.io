package kr.co.dh996.project11re.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChampDTO {

	private String champID;
	private String champName;
	private List<String> champTags;
}
