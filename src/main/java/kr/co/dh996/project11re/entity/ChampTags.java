package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "champs_tags")
public class ChampTags {

	@ManyToOne
	@JoinColumn(name = "champ_version", referencedColumnName = "champ_version", foreignKey = @ForeignKey(name = "fk_version_tags"))
	private String champ_version;
	
	@ManyToOne
	@JoinColumn(name = "champ_id", referencedColumnName = "champ_id", foreignKey = @ForeignKey(name = "fk_name_tags"))
	private String champ_id;
	
	@Column(name = "champ_tags")
	private String champ_tags;
}
