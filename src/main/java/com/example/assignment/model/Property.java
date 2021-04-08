package com.example.assignment.model;

import com.example.assignment.dto.PropertyDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@RequiredArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String propertyName;

    @NotNull
    @Column
    private String location;

    @NotNull
    @Column
    private String size;

    @NotNull
    @Column(nullable = false)
    private Boolean status;

    @CreatedDate
    @Column
    private LocalDateTime created;

    @LastModifiedDate
    @Column
    private LocalDateTime lastModified;

    public Property(@NotNull final PropertyDto dto) {
        this.mapDTO(dto);
    }

    public void mapDTO(@NotNull final PropertyDto dto) {
        this.setId(dto.getId());
        this.setPropertyName(dto.getPropertyName());
        this.setLocation(dto.getLocation());
        this.setSize(dto.getSize());
        this.setStatus(dto.getStatus());
    }


}
