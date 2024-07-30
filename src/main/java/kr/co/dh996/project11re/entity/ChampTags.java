package kr.co.dh996.project11re.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "champ_tags")
public class ChampTags {
	
	@Id
    @Column(name = "pk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

	@ManyToOne
	@JoinColumn(name = "champ_version", referencedColumnName = "champ_version", foreignKey = @ForeignKey(name = "fk_version_tags"))
	private ChampVersion champVersion;
	
	@ManyToOne
	@JoinColumn(name = "champ_id", referencedColumnName = "champ_id", foreignKey = @ForeignKey(name = "fk_name_tags"))
	private ChampName champId;
	
	@Column(name = "champ_tags")
	private String champTags;
}
