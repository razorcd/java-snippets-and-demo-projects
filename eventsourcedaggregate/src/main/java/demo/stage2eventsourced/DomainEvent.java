package demo.stage2eventsourced;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode
@ToString
public abstract class DomainEvent {
    private String type = this.getClass().getName();
    private Instant createdAt = Instant.now();
}
