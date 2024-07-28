package uz.project.rentalplaces.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "size")
    private long size;

    @Column(name = "new_name")
    private String newName;

    @Column(name = "type")
    private String type;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "path")
    private String path;

    @CreationTimestamp
    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "deleted_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime deletedAt;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Attachment(@JsonProperty("id") Long id,
                      @JsonProperty("origin_name") String originName,
                      @JsonProperty("size") long size,
                      @JsonProperty("new_name") String newName,
                      @JsonProperty("type") String type,
                      @JsonProperty("content_type") String contentType,
                      @JsonProperty("path") String path,
                      @JsonProperty("created_at") Timestamp createdAt,
                      @JsonProperty("updated_at") Timestamp updatedAt,
                      @JsonProperty("deleted_at") Timestamp deletedAt,
                      @JsonProperty("deleted") Boolean deleted) {
        this.id = id;
        this.originName = originName;
        this.size = size;
        this.newName = newName;
        this.type = type;
        this.contentType = contentType;
        this.path = path;
        this.createdAt = createdAt.toLocalDateTime();
        this.updatedAt = updatedAt.toLocalDateTime();
        this.deletedAt = deletedAt.toLocalDateTime();
        this.deleted = deleted;
    }
}
