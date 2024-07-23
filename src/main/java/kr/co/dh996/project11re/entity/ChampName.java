package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "champ_name")
public class ChampName {

	@ManyToOne
	@JoinColumn(name = "champ_version", referencedColumnName = "champ_version", foreignKey = @ForeignKey(name = "fk_version_name"))
	private ChampVersion champVersion;
	
	@Id
	@Column(name = "champ_id")
	private String champId;
	
	@Column(name = "champ_name")
	private String champName;
}
