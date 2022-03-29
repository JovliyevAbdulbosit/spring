package com.security.jwtlearning.Urls.vs;
import java.util.Set;
public class AccountBody {
    private Set<Long>cardId;

    public Set<Long> getCardId() {
        return cardId;
    }

    public void setCardId(Set<Long> cardId) {
        this.cardId = cardId;
    }
}
