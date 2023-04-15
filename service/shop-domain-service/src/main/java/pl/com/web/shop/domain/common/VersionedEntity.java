package pl.com.web.shop.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class VersionedEntity {

    @Id
    @GeneratedValue
    @Column(
            columnDefinition = "uuid",
            updatable = false
    )
    private UUID id;

    @NotNull
    private OffsetDateTime created;

    @Nullable
    private OffsetDateTime modified;

    @NotNull
    @Version
    @Min(0L)
    private Integer version;

    @PrePersist
    void onCreate() {
        created = OffsetDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        modified = OffsetDateTime.now();
    }
}
