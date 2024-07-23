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
@Table(name = "champ_version")
public class ChampVersion {

	@Id
	@Column(name = "champ_version")
	private String champVersion;
	
	@Column(name = "champ_updatetime", nullable = false)
    private LocalDateTime champUpdatetime;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
    	champUpdatetime = LocalDateTime.now();
    }

}
