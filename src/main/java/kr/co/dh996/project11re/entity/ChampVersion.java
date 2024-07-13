package kr.co.dh996.project11re.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "champs_version")
public class ChampVersion {

	@Id
	@Column(name = "champ_version")
	private String champ_version;
	
	@Column(name = "champ_updatetime", nullable = false)
    private LocalDateTime champ_updatetime;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
    	champ_updatetime = LocalDateTime.now();
    }

}
