package com.luffycan.commonutils.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.luffycan.commonutils.utils.JWTTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/04 20:02
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final static String AUTH_HEADER = "Authorization";
    private final static String AUTH_HEADER_TYPE = "Bearer";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token from header:  Authorization: Bearer <token>
        String authHeader = request.getHeader(AUTH_HEADER);
        if (Objects.isNull(authHeader) || !authHeader.startsWith(AUTH_HEADER_TYPE)){
            filterChain.doFilter(request,response);
            return;
        }

        String authToken = authHeader.split(" ")[1];
        log.info("authToken:{}" , authToken);
//        JWT.decode(authToken).getToken();
        if(!JWTTokenUtils.checkToken(authToken)){
            log.info("invalid token:{}",authToken);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println("认证失败");
            response.getWriter().flush();
            return;
        }

//        verify token
//        if (!JWTUtil.verify(authToken, MyConstant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8))) {
//            log.info("invalid token");
//            filterChain.doFilter(request,response);
//            return;
//        }
       final String username = JWT.decode(authToken).getClaim("username").toString();


//        final String userName = (String) JWTUtil.parseToken(authToken).getPayload("username");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//         注意，这里使用的是3个参数的构造方法，此构造方法将认证状态设置为true
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //将认证过了凭证保存到security的上下文中以便于在程序中使用
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
