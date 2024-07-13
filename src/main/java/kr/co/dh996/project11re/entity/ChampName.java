package kr.co.dh996.project11re.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "champs_name")
public class ChampName {

	@ManyToOne
	@JoinColumn(name = "champ_version", referencedColumnName = "champ_version", foreignKey = @ForeignKey(name = "fk_version_name"))
	private String champ_version;
	
	@Id
	@Column(name = "champ_id")
	private String champ_id;
	
	@Column(name = "champ_name")
	private String champ_name;
}
