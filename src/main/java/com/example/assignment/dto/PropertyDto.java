package com.example.assignment.dto;

import com.example.assignment.model.Property;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class PropertyDto {

    private Long id;

    @NotNull
    private String propertyName;

    @NotNull
    private String location;

    @NotNull
    private String size;

    @NotNull
    @Column(nullable = false)
    private Boolean status;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastModified;

    public PropertyDto(@NotNull final Property property) {
        this.setLastModified(property.getLastModified());
        this.setCreated(property.getCreated());
        this.setId(property.getId());
        this.setPropertyName(property.getPropertyName());
        this.setLocation(property.getLocation());
        this.setSize(property.getSize());
        this.setStatus(property.getStatus());
    }

    /**
     * @param propertyList list of property to be mapped
     * @return list of propertyDto
     */
    public static List<PropertyDto> mapEntityList(@NonNull final List<Property> propertyList) {
        List<PropertyDto> propertyDTOList = new ArrayList<>();
        for (Property property : propertyList) {
            propertyDTOList.add(new PropertyDto(property));
        }
        return propertyDTOList;
    }

}
