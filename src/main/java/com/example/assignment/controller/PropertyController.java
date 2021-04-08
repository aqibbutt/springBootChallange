package com.example.assignment.controller;

import com.example.assignment.dto.PagedResults;
import com.example.assignment.dto.PropertyCriteria;
import com.example.assignment.dto.PropertyDto;
import com.example.assignment.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RequestMapping("/api/property")
@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity addProperty(@RequestBody PropertyDto request) {
        return ResponseEntity.ok(propertyService.addProperty(request));
    }

    @GetMapping
    public ResponseEntity<PagedResults<PropertyDto>> getProperty(@NotNull final PropertyCriteria criteria) {
        return ResponseEntity.ok(propertyService.getProperties(criteria));
    }

    @GetMapping("{id}")
    public ResponseEntity getProperty(@PathVariable("id") @NotNull final Long id) {
        return ResponseEntity.ok(propertyService.getProperties(id));
    }

    @PatchMapping
    public ResponseEntity editProperty(@RequestBody final PropertyDto dto) {
        ;
        return ResponseEntity.ok(propertyService.editProperty(dto));
    }

    @PatchMapping("{id}/approve/{check}")
    public ResponseEntity approveProperty(@PathVariable("id") @NotNull final Long id,
                                          @PathVariable("check") @NotNull final Boolean check) {
        propertyService.approveProperty(check, id);
        return ResponseEntity.ok("Success");
    }

}
