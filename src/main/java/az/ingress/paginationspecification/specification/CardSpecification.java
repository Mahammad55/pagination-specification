package az.ingress.paginationspecification.specification;

import az.ingress.paginationspecification.dto.CardCriteria;
import az.ingress.paginationspecification.entity.Card;
import az.ingress.paginationspecification.entity.Card_;
import az.ingress.paginationspecification.util.CardPredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CardSpecification implements Specification<Card> {

    private CardCriteria cardCriteria;

    @Override
    public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = CardPredicateUtil.builder()
                .addNullSafety(cardCriteria.getCardName(), cardName -> cb.equal(root.get(Card_.cardName), cardName))
                .addNullSafety(cardCriteria.getCardNumber(), cardNumber -> cb.like(root.get(Card_.cardNumber), "%" + cardNumber + "%"))
                .addNullSafety(cardCriteria.getExpirationDateFrom(), expirationDateFrom -> cb.greaterThanOrEqualTo(root.get(Card_.expirationDate), expirationDateFrom))
                .addNullSafety(cardCriteria.getExpirationDateTo(), expirationDateTo -> cb.lessThanOrEqualTo(root.get(Card_.expirationDate), expirationDateTo))
                .build();
        return cb.and(predicates);
    }
}
