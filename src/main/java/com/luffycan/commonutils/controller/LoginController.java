package com.luffycan.commonutils.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.luffycan.commonutils.common.CommonConstant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/04 11:32
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    //    @Autowired
//    AuthenticationManager authenticationManager;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String login(@RequestBody UserInfo req) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        authenticationManager.authenticate(authenticationToken);

        //上一步没有抛出异常说明认证成功，我们向用户颁发jwt令牌

        return JWT.create()
                .withClaim("username", req.getUsername())
                .withClaim("password", req.getPassword())
                .withClaim("role", req.getRole())
                .sign(Algorithm.HMAC256(CommonConstant.JWT_SIGN_KEY));

    }

}
