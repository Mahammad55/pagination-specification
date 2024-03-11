package az.ingress.paginationspecification.service;

import az.ingress.paginationspecification.dto.CardCriteria;
import az.ingress.paginationspecification.dto.PageCriteria;
import az.ingress.paginationspecification.dto.PageableCardResponse;

public interface CardService {
    PageableCardResponse getAllCards(PageCriteria pageCriteria, CardCriteria cardCriteria);
}
