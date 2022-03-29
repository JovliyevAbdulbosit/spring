package com.security.jwtlearning.Urls;

import com.security.jwtlearning.domen.UserTable;
import com.security.jwtlearning.security.SecurityUtil;
import com.security.jwtlearning.services.UserTableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import javax.swing.text.html.Option;

@RestController
@RequestMapping("/user")
public class UserTableUrl {
    private final UserTableService userTableService ;

    public UserTableUrl(UserTableService userTableService) {
        this.userTableService = userTableService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody UserTable userTable){
        if(!checkPassword(userTable.getPassword())){
            return new ResponseEntity("parol uzunligi 4 tadan kam kiritdingiz", HttpStatus.BAD_REQUEST);
        }
        if(userTableService.checkUser(userTable.getUsername())){
            return new ResponseEntity("Bu user oldin ro'yxatdan o'tgan", HttpStatus.BAD_REQUEST) ;
        }
        return ResponseEntity.ok(userTableService.create(userTable));


    }
    private boolean checkPassword(String password){

        return (password.length() >= 4 );

    }
    @GetMapping("/test")
    public ResponseEntity test(){
        Optional user = SecurityUtil.getCurrentUser();

       UserTable currentuser = userTableService.getUser((String) user.get());
        System.out.println(currentuser);
        return ResponseEntity.ok(currentuser);
    }
}
