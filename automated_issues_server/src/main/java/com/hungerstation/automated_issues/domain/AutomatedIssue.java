package com.hungerstation.automated_issues.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Entity
@Table(name = "automated_issues",
        indexes = {
                @Index(name = "index_automated_issues_on_related_to_type_and_related_to_id", columnList = "related_to_id,related_to_type"
                )
        }
)
public class AutomatedIssue {


    public enum StatusEnum {

        OPEN(0), CLOSED(1);

        private int number;

        private StatusEnum(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public static StatusEnum forNumber(int number) {
            for (StatusEnum e : values()) {
                if (e.getNumber() == number) {
                    return e;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "trigger_key")
    private Integer triggerKey;

    @NotNull
    @Column(name = "related_to_type")
    private String relatedToType;

    @NotNull
    @Column(name = "related_to_id")
    private Long relatedToId;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public Integer getTriggerKey() {
        return triggerKey;
    }

    public void setTriggerKey(Integer triggerKey) {
        this.triggerKey = triggerKey;
    }

    public String getRelatedToType() {
        return relatedToType;
    }

    public void setRelatedToType(String relatedToType) {
        this.relatedToType = relatedToType;
    }

    public Long getRelatedToId() {
        return relatedToId;
    }

    public void setRelatedToId(Long relatedToId) {
        this.relatedToId = relatedToId;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AutomatedIssue{");
        sb.append("triggerKey=").append(triggerKey);
        sb.append(", relatedToType='").append(relatedToType).append('\'');
        sb.append(", relatedToId=").append(relatedToId);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutomatedIssue that = (AutomatedIssue) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (triggerKey != null ? !triggerKey.equals(that.triggerKey) : that.triggerKey != null) return false;
        if (relatedToType != null ? !relatedToType.equals(that.relatedToType) : that.relatedToType != null)
            return false;
        if (relatedToId != null ? !relatedToId.equals(that.relatedToId) : that.relatedToId != null) return false;
        if (status != that.status) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return updatedAt != null ? updatedAt.equals(that.updatedAt) : that.updatedAt == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (triggerKey != null ? triggerKey.hashCode() : 0);
        result = 31 * result + (relatedToType != null ? relatedToType.hashCode() : 0);
        result = 31 * result + (relatedToId != null ? relatedToId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
