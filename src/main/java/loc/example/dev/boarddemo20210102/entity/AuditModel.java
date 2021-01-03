package loc.example.dev.boarddemo20210102.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class AuditModel {

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}
