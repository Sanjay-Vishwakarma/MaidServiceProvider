package com.maid.service.provider.util;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

public class Functions {


    public static String extractClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isEmpty() && !"unknown".equalsIgnoreCase(forwarded)) {
            return forwarded.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    public static UserAgentDetails extractUserAgentDetails(HttpServletRequest request) {
        String userAgentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

        UserAgentDetails details = new UserAgentDetails();
        details.setUserAgent(userAgentString);
        details.setOperatingSystem(userAgent.getOperatingSystem().getName());
        details.setDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());

        return details;
    }

    @Data
    public static class UserAgentDetails {
        private String userAgent;
        private String operatingSystem;
        private String deviceType;
    }

}
