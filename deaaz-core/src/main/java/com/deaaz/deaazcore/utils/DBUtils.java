package com.deaaz.deaazcore.utils;

import com.deaaz.deaazcore.dto.CriteriaDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtils {

    public static Predicate[] createPredicates(List<CriteriaDTO> criterias, CriteriaBuilder builder, Root<?> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (CriteriaDTO criteria : criterias) {
            switch (criteria.getOperator()) {
                case EQUAL:
                    predicates.add(builder.equal(root.get(criteria.getField()), criteria.getValues().get(0)));
                    break;
                case NOT_EQUAL:
                    predicates.add(builder.notEqual(root.get(criteria.getField()), criteria.getValues().get(0)));
                    break;
                case LIKE:
                    predicates.add(builder.like(root.get(criteria.getField()), "%" + criteria.getValues().get(0) + "%"));
                    break;
                case IN:
                    predicates.add(root.get(criteria.getField()).in(criteria.getValues()));
                    break;
                case NOT_IN:
                    predicates.add(builder.not(root.get(criteria.getField()).in(criteria.getValues())));
                    break;
                case GREATER_THAN:
                    if (criteria.getValues().get(0) instanceof Date) {
                        predicates.add(builder.greaterThan(root.<Date>get(criteria.getField()),
                                (Date) criteria.getValues().get(0)));
                    }
                    if (criteria.getValues().get(0) instanceof Number) {
                        predicates.add(builder.gt(root.get(criteria.getField()), (Number) criteria.getValues().get(0)));
                    }
                    break;
                case GREATER_THAN_EQUAL:
                    if (criteria.getValues().get(0) instanceof Date) {
                        predicates.add(builder.greaterThanOrEqualTo(root.<Date>get(criteria.getField()),
                                (Date) criteria.getValues().get(0)));
                    }
                    if (criteria.getValues().get(0) instanceof Number) {
                        predicates.add(builder.ge(root.get(criteria.getField()), (Number) criteria.getValues().get(0)));
                    }
                    break;
                case LESS_THAN:
                    if (criteria.getValues().get(0) instanceof Date) {
                        predicates.add(
                                builder.lessThan(root.<Date>get(criteria.getField()), (Date) criteria.getValues().get(0)));
                    }
                    if (criteria.getValues().get(0) instanceof Number) {
                        predicates.add(builder.lt(root.get(criteria.getField()), (Number) criteria.getValues().get(0)));
                    }
                    break;
                case LESS_THAN_EQUAL:
                    if (criteria.getValues().get(0) instanceof Date) {
                        predicates.add(builder.lessThanOrEqualTo(root.<Date>get(criteria.getField()),
                                (Date) criteria.getValues().get(0)));
                    }
                    if (criteria.getValues().get(0) instanceof Number) {
                        predicates.add(builder.le(root.get(criteria.getField()), (Number) criteria.getValues().get(0)));
                    }
                    break;
                default:
                    break;
            }
        }
        Predicate[] pred = predicates.toArray(new Predicate[] {});
        return pred;
    }
}
