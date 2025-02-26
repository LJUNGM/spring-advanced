package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.expert.domain.user.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;


@Component
public class AdminInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler) throws Exception {

        String userRole =(String) request.getAttribute("userRole");
        logger.info("userRole: {}",userRole);

        if(!UserRole.ADMIN.name().equals(userRole)){
            logger.error("비인가 사용자");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "인증 오류");
            return false;
        }

        String url = request.getRequestURI();
        Instant time = Instant.now();
        logger.info("인증 성공 | URL: {}, TIME: {}",url, time);

        return true;
    }


}
