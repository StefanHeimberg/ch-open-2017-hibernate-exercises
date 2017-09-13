package org.thoughts.on.java.model;

import java.time.LocalDateTime;

public interface AuditedEntity {

	void setLastUpdate(LocalDateTime lastUpdate);
	LocalDateTime getLastUpdate();
}
