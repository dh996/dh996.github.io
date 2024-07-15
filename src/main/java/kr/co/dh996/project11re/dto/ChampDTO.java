package kr.co.dh996.project11re.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChampDTO {

	private String champID;
	private String champName;
	private List<String> champTags;
	
	public ChampDTO(String champId, String champName, List<String> tags) {
        this.champID = champId;
        this.champName = champName;
        this.champTags = tags;
    }
}
