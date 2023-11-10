package com.luffycan.commonutils.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.luffycan.commonutils.common.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author : luffy
 * @version : 1.0
 * @date : 2023/11/06 9:42
 */
@Slf4j
public class JWTTokenUtils {


    private static final Algorithm ALGORITHM = Algorithm.HMAC256(CommonConstant.JWT_SIGN_KEY);

    public static boolean checkToken(String token) {
        DecodedJWT decode = JWT.decode(token);
        byte[] sign = ALGORITHM.sign(decode.getHeader().getBytes(StandardCharsets.UTF_8), decode.getPayload().getBytes(StandardCharsets.UTF_8));
        String s = Base64.getUrlEncoder().withoutPadding().encodeToString(sign);
        return decode.getSignature().equals(s);
    }
}
