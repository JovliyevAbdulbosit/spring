package com.security.jwtlearning.Urls;

import com.security.jwtlearning.Urls.vs.AccountBody;
import com.security.jwtlearning.domen.AccountCardUser;
import com.security.jwtlearning.domen.Card;
import com.security.jwtlearning.domen.UserTable;

import com.security.jwtlearning.security.SecurityUtil;
import com.security.jwtlearning.services.AccountService;
import java.util.*;

import com.security.jwtlearning.services.CardService;
import com.security.jwtlearning.services.UserTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final CardService cardService;
    private final UserTableService userTableService;
    public AccountController(AccountService accountService,  CardService cardService, UserTableService userTableService) {
        this.accountService = accountService;
        this.cardService = cardService;

        this.userTableService = userTableService;
    }
    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestBody AccountBody accountBody){
        Optional user = SecurityUtil.getCurrentUser();

        UserTable currentuser = userTableService.getUser((String) user.get());
        Set<Card> cards = new HashSet(){};
        for ( Long i: accountBody.getCardId()) {
           Card card = cardService.findByCardId(i);
            cards.add(card);
        }
        AccountCardUser accountCardUser = new AccountCardUser();
        accountCardUser.setCards(cards);
        accountCardUser.setUserid(currentuser);
        AccountCardUser data = accountService.accountCreate(accountCardUser);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/getAccount")
    public ResponseEntity get(){
        return ResponseEntity.ok( accountService.getAll());
    }

    @PutMapping("/o'tkazma")
    public ResponseEntity update(@RequestParam("sum") Long sum ,@RequestParam("card") Long cardId ,
                                 @RequestParam("tra") Long tranzak){
        System.out.println(sum);
        System.out.println(cardId);
        Optional user = SecurityUtil.getCurrentUser();
        UserTable currentuser = userTableService.getUser((String) user.get());
        AccountCardUser accountCardUser = accountService.findByUserid(currentuser);
        Card currentCard = cardService.findByCardId(cardId);
        if(accountCardUser != null && accountCardUser.getCards().contains(currentCard)){
            System.out.println(accountCardUser.getCards().contains(currentCard));
          if(currentCard.getBalance() >= sum){
             Long balance = currentCard.getBalance()-sum;
             Card tran = cardService.findByCardId(tranzak);

              currentCard.setBalance(balance);
             Long balanceTr = tran.getBalance();
             tran.setBalance(balanceTr+sum);
              System.out.println(currentCard.getClass());
              cardService.save(currentCard);
              cardService.save(tran);

              return ResponseEntity.ok(currentCard);
          }

          else
          {
              return ResponseEntity.ok("sizda mablag' yetarli emas");
          }
        } else if(!accountCardUser.getCards().contains(cardId)){return ResponseEntity.ok("boshqa ");}
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/add")
    public ResponseEntity update(@RequestParam("card") Long card){
        Optional user = SecurityUtil.getCurrentUser();
        UserTable currentuser = userTableService.getUser((String) user.get());
        AccountCardUser accountCardUser = accountService.findByUserid(currentuser);
        Card currentCard = cardService.findByCardId(card);
        if(currentCard != null){
            Set<Card> cards =accountCardUser.getCards();
        cards.add(currentCard);
        accountCardUser.setCards(cards);
       AccountCardUser result =  accountService.accountCreate(accountCardUser);
        return ResponseEntity.ok(result);}
        else{ return ResponseEntity.ok("bunday karta mavjud emas");}
    }
    @GetMapping("/getBalance")
    public ResponseEntity getBalance(@RequestParam("card") Long card){
        Optional user = SecurityUtil.getCurrentUser();
        UserTable currentuser = userTableService.getUser((String) user.get());
        AccountCardUser accountCardUser = accountService.findByUserid(currentuser);
        Card currentCard = cardService.findByCardId(card);
        if(accountCardUser.getCards().contains(currentCard) && accountCardUser != null){
          return   ResponseEntity.ok(cardService.findByCardId(card));
        }else if(!accountCardUser.getCards().contains(currentCard)){return ResponseEntity.ok("karta nomeri xato");}
        return ResponseEntity.badRequest().build();
    }
}
