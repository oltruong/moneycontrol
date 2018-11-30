package com.oltruong.moneycontrol;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class HttpUtils {

    private HttpUtils() {
        //Utility class
    }

    public static void initRequestAttributes() {
        HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);

        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockHttpServletRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
    }
}
