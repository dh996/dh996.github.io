package kr.co.dh996.project11re.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
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
