package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

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
