package com.security.jwtlearning.Urls;

import com.security.jwtlearning.Urls.vs.LoginVm;
import com.security.jwtlearning.domen.UserTable;
import com.security.jwtlearning.repository.UserTableRepository;
import com.security.jwtlearning.security.JwtTokenProvayder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class JwtControler {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvayder jwtTokenProvayder;
    private  final UserTableRepository userTableRepository;

    public JwtControler(AuthenticationManager authenticationManager, JwtTokenProvayder jwtTokenProvayder, UserTableRepository userTableRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvayder = jwtTokenProvayder;
        this.userTableRepository = userTableRepository;
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginVm loginVm ){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVm.getUsername(),loginVm.getPassword()));
        UserTable user = userTableRepository.findByUsername(loginVm.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("bunday foydalanuvchi mavjud emas");
        }
        String token = jwtTokenProvayder.createToke(user.getUsername() ,user.getRoles());
        Map<Object,Object> map = new HashMap<>();
        map.put("username" , user.getUsername());
        map.put("token" , token);

        return new ResponseEntity(map , HttpStatus.OK);
    }

}
