package example.astralifetesting.auditable;

import example.astralifetesting.model.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> extends BaseEntity {

    @CreatedBy
    @Column(name = "created_by")
    protected U createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "from_date")
    protected Date from_date;

    @LastModifiedBy
    @Column(name = "modified_by")
    protected U lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "to_date")
    protected Date to_date;
}
