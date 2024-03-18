package vn.smartshop.server.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Persister;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @CreationTimestamp
  @Column
  @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
  private Timestamp createdTimestamp;

  @UpdateTimestamp
  @Column
  @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
  private Timestamp updatedTimestamp;

  @Column(nullable = false)
  @CreatedBy
  private String createdBy;

  @Column(nullable = false)
  @LastModifiedBy
  private String updatedBy;

  @Column
  @org.hibernate.annotations.ColumnDefault("true")
  private Boolean active;

}
