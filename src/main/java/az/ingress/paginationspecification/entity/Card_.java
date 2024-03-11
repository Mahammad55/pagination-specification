package az.ingress.paginationspecification.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Card.class)
public abstract class Card_ {
    public static volatile SingularAttribute<Card, Long> id;
    public static volatile SingularAttribute<Card, String> cardName;
    public static volatile SingularAttribute<Card, String> cardNumber;
    public static volatile SingularAttribute<Card, Date> expirationDate;
}
