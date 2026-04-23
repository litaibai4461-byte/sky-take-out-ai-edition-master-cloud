package com.sky.filters;

import com.sky.config.AuthProperties;
import com.sky.constant.JwtClaimsConstant;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthUserGlobalFilter implements GlobalFilter, Ordered {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final JwtProperties jwtProperties;
    private final AuthProperties authProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        // 是否是排除路径
        if (isExclude(path)){
            return chain.filter(exchange);
        }
        // 获取令牌
        String token = request.getHeaders().getFirst(jwtProperties.getUserTokenName());
        Claims claims=null;
        //尝试解析
        try {
            claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
        }catch (Exception e){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
        System.out.println("userId:"+userId);
        //5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header( "user-info", userInfo))
                .build();
        return chain.filter(swe);
    }

    private boolean isExclude(String path) {
        if (antPathMatcher.match("/admin/**", path)){
            return true;
        }
        for (String excludePath : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(excludePath, path)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
