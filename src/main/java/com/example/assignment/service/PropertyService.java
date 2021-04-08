package com.example.assignment.service;

import com.example.assignment.dto.PagedResults;
import com.example.assignment.dto.PropertyCriteria;
import com.example.assignment.dto.PropertyDto;
import com.example.assignment.model.Property;
import com.example.assignment.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final EntityManager entityManager;

    /**
     * Calculates how many pages there are.
     *
     * @param totalElements How many elements are there total ignoring pages.
     * @param pageSize      What the size of the page is.
     * @return Total number of pages.
     */
    @NonNull
    public static Long pageCalculator(Long totalElements, Integer pageSize) {
        Long totalPages = totalElements / pageSize;
        if (totalElements % pageSize != 0) totalPages++;
        return totalPages;
    }

    public Property addProperty(@NotNull final PropertyDto dto) {
        if (dto.getId() != null && propertyRepository.findById(dto.getId()).isPresent()) {
            throw new IllegalArgumentException("Property Already exist.");
        }
        Property property = new Property(dto);
        return propertyRepository.save(property);
    }

    /**
     * edit the property
     *
     * @param dto dto of the property
     * @return
     */
    public Property editProperty(@NotNull final PropertyDto dto) {
        Property property = getProperty(dto.getId());
        property.mapDTO(dto);
        return propertyRepository.save(property);
    }

    /**
     * approve or decline property
     *
     * @param bool boolean check
     * @param id   id of proeprty
     */
    public void approveProperty(@NotNull final Boolean bool, @NotNull final Long id) {
        Property property = getProperty(id);
        property.setStatus(bool);
        propertyRepository.save(property);
    }

    /**
     * get list of properties depending on the criteria
     *
     * @return list of Properties dto with pagination
     */
    public PagedResults<PropertyDto> getProperties(@NotNull final PropertyCriteria propertyCriteria) {
        // create queries
        final TypedQuery<Property> query = propertyCriteria.buildSelectQuery(entityManager);
        final Query countQuery = propertyCriteria.buildCountQuery(entityManager);
        // run queries
        query
                .setFirstResult(propertyCriteria.getPageNumber() * propertyCriteria.getPageSize())
                .setMaxResults(propertyCriteria.getPageSize());
        final List<Property> pagedEntities = query.getResultList();
        final Long entityCount = (long) countQuery.getSingleResult();
        // return results with page info
        return new PagedResults<>(
                pageCalculator(entityCount, propertyCriteria.getPageSize()),
                entityCount,
                PropertyDto.mapEntityList(pagedEntities));
    }

    /**
     * get Property by Id
     *
     * @param id id of the property
     * @return get the id of the property
     */
    public PropertyDto getProperties(@NotNull final Long id) {
        Property property = getProperty(id);
        return new PropertyDto(property);
    }

    private Property getProperty(Long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Property does not exist."));
    }


}
