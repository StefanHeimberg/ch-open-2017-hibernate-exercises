package org.thoughts.on.java.model;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class LastUpdateListener {

	@PrePersist
	@PreUpdate
	public void updateTimestamp(AuditedEntity entity) {
		entity.setLastUpdate(LocalDateTime.now());
	}
}
