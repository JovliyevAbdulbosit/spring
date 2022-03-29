package com.security.jwtlearning.domen;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class AccountCardUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch=FetchType.LAZY )
    private Set<Card> cards;
    @OneToOne
    @NotNull
    @JoinColumn( unique = true )
    private UserTable userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public UserTable getUserid() {
        return userid;
    }

    public void setUserid(UserTable userid) {
        this.userid = userid;
    }
}
