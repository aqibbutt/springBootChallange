package com.example.assignment.service;

import com.example.assignment.dto.PropertyDto;
import com.example.assignment.model.Property;
import com.example.assignment.repository.PropertyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class PropertyTest {

    @Autowired
    private PropertyService propertyService;

    @MockBean
    private PropertyRepository propertyRepository;

    @Test
    @DisplayName("Test save propert")
    void testSave() {
        Property property = new Property();
        property.setStatus(true);
        property.setPropertyName("ABC");
        property.setSize("ABC");
        property.setLocation("ABC");
        doReturn(property).when(propertyRepository).save(any());

        // Execute the service call
        Property result = propertyService.addProperty(new PropertyDto(property));

        // Assert the response
        Assertions.assertNotNull(result, "The saved property should not be null");
        Assertions.assertEquals("ABC", result.getPropertyName(), "The property name should be incremented");
    }

    @Test
    @DisplayName("Test Get property")
    void testGet() {
        Property property = new Property();
        property.setStatus(true);
        property.setPropertyName("ABC");
        property.setSize("ABC");
        property.setLocation("ABC");
        doReturn(Optional.of(property)).when(propertyRepository).findById(any());
        // Execute the service call
        PropertyDto result = propertyService.getProperties(1L);

        // Assert the response
        Assertions.assertNotNull(result, "The saved property should not be null");
        Assertions.assertEquals("ABC", result.getPropertyName(), "The name should not be null");
    }

    @Test
    @DisplayName("Test Get property")
    void testEdit() {
        Property property = new Property();
        property.setStatus(true);
        property.setPropertyName("TEST");
        property.setSize("ABC");
        property.setLocation("ABC");
        property.setId(1L);

        doReturn(Optional.of(property)).when(propertyRepository).findById(any());
        doReturn(property).when(propertyRepository).save(any());

        Property result = propertyService.editProperty(new PropertyDto(property));

        // Assert the response
        Assertions.assertNotNull(result, "The Edit property should not be null");
        Assertions.assertEquals("TEST", result.getPropertyName(), "The name should not be null");
    }

}
