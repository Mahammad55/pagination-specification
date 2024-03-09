package az.ingress.paginationspecification.specification;

import az.ingress.paginationspecification.dto.SearchCriteria;
import az.ingress.paginationspecification.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification implements Specification<Student> {
    private final List<SearchCriteria> searchCriteriaList;

    public StudentSpecification() {
        this.searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria) {
        searchCriteriaList.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList) {
            switch (criteria.getSearchOperation()) {
                case GREATER_THAN ->
                        predicateList.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                case GREATER_THAN_EQUAL ->
                        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN ->
                        predicateList.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN_EQUAL ->
                        predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                case EQUAL ->
                        predicateList.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue().toString()));
                case NOT_EQUAL ->
                        predicateList.add(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString()));
                case MATCH ->
                        predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                case MATCH_START ->
                        predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase()));
                case MATCH_END ->
                        predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
                case IN ->
                        predicateList.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                case NOT_IN ->
                        predicateList.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()).not());
                case BETWEEN -> {
                    List<Integer> value = (List<Integer>) (criteria.getValue());
                    predicateList.add(criteriaBuilder.between(root.get(criteria.getKey()), value.get(0), value.get(1)));
                }
                case NOT_BETWEEN -> {
                    List<Integer> value = (List<Integer>) (criteria.getValue());
                    predicateList.add(criteriaBuilder.between(root.get(criteria.getKey()), value.get(0), value.get(1)).not());
                }
            }
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
