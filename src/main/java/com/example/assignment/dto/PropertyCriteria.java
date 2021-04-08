package com.example.assignment.dto;

import com.example.assignment.model.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class PropertyCriteria extends PagingAndSorting {

    private Long id;

    private String propertyName;

    private String location;

    private String size;

    private String search;

    public static String buildOrderQuery(final String identifier, PagingAndSorting pagingAndSorting) {
        if (pagingAndSorting.getSortBy().size() != pagingAndSorting.getSortDir().size()) {
            throw new IllegalArgumentException("sortDir and sortBy lengths do not match");
        }
        StringBuilder sb = new StringBuilder("ORDER BY ");
        for (int i = 0; i < pagingAndSorting.getSortBy().size(); i++) {
            sb.append(String.format("%s.%s %s", identifier, pagingAndSorting.getSortBy().get(i),
                    pagingAndSorting.getSortDir().get(i)));
            if (i != pagingAndSorting.getSortBy().size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Creates a select query based on the criteria given.
     *
     * @return select query string.
     */
    @NonNull
    public String buildSelectString() {
        final StringBuilder q = new StringBuilder("SELECT DISTINCT p FROM Property p WHERE 1=1 ");

        if (this.id != null) {
            q.append(String.format("AND p.id='%d' ", this.getId()));
        }

        if (this.size != null) {
            q.append(String.format("AND lower(p.size)='%s' ", this.size.toLowerCase()));
        }

        if (this.propertyName != null) {
            q.append(String.format("AND lower(p.propertyName)='%s' ", this.propertyName.toLowerCase()));
        }

        if (this.location != null) {
            q.append(String.format("AND lower(p.location)='%s' ", this.location.toLowerCase()));
        }

        if (this.search != null) {
            String s = "AND (lower(p.size) LIKE '%search%' " +
                    "OR lower(p.propertyName) LIKE '%search%' " +
                    "OR lower(p.location) LIKE '%search%' " +
                    ") ";
            q.append(s.replaceAll("search", this.search.toLowerCase()));
        }

        q.append(buildOrderQuery("p", this));

        return q.toString();
    }

    /**
     * Changes the select query to a count by adding a count command and ignoring the order clause.
     *
     * @return count query for the given criteria.
     */
    @NonNull
    public String buildCountString() {
        final String queryString = this.buildSelectString();
        return queryString.substring(0, queryString.indexOf("ORDER BY")).replace("SELECT DISTINCT p", "SELECT COUNT(DISTINCT p)");
    }

    @NonNull
    public TypedQuery<Property> buildSelectQuery(@NonNull final EntityManager entityManager) {
        String queryString = buildSelectString();
        return entityManager.createQuery(queryString, Property.class);
    }


    @NonNull
    public Query buildCountQuery(@NonNull final EntityManager entityManager) {
        String countQueryString = this.buildCountString();
        return entityManager.createQuery(countQueryString);
    }

}
