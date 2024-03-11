package az.ingress.paginationspecification.controller;

import az.ingress.paginationspecification.dto.CardCriteria;
import az.ingress.paginationspecification.dto.PageCriteria;
import az.ingress.paginationspecification.dto.PageableCardResponse;
import az.ingress.paginationspecification.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<PageableCardResponse> getAllCards(PageCriteria pageCriteria, CardCriteria cardCriteria) {
        return ResponseEntity.ok(cardService.getAllCards(pageCriteria,cardCriteria));
    }
}
