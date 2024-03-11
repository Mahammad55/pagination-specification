package az.ingress.paginationspecification.service.impl;

import az.ingress.paginationspecification.dto.CardCriteria;
import az.ingress.paginationspecification.dto.PageCriteria;
import az.ingress.paginationspecification.dto.PageableCardResponse;
import az.ingress.paginationspecification.entity.Card;
import az.ingress.paginationspecification.repository.CardRepository;
import az.ingress.paginationspecification.service.CardService;
import az.ingress.paginationspecification.specification.CardSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static az.ingress.paginationspecification.enums.CriteriaConstant.PAGE_NUMBER_DEFAULT_VALUE;
import static az.ingress.paginationspecification.enums.CriteriaConstant.PAGE_SIZE_DEFAULT_VALUE;
import static az.ingress.paginationspecification.enums.CriteriaConstant.SORT_DEFAULT_VALUE;
import static az.ingress.paginationspecification.mapper.PageableMapper.mapToCardPageableResponse;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public PageableCardResponse getAllCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        var pageNumber = pageCriteria.getPageNumber() == null ? PAGE_NUMBER_DEFAULT_VALUE : pageCriteria.getPageNumber();
        var pageSize = pageCriteria.getPageSize() == null ? PAGE_SIZE_DEFAULT_VALUE : pageCriteria.getPageSize();
        var sort = pageCriteria.getSort() == null ? SORT_DEFAULT_VALUE : pageCriteria.getSort();

        Pageable pageable;
        switch (sort[1]) {
            case "asc" -> pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort[0]));
            case "desc" -> pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort[0]).descending());
            default -> throw new RuntimeException("Please enter the valid sort direction like {asc,desc}");
        }

        Page<Card> cardPage = cardRepository.findAll(new CardSpecification(cardCriteria), pageable);
        return mapToCardPageableResponse(cardPage);
    }
}
