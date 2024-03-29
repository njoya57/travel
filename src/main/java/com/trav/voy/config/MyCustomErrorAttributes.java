/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.config;

/**
 *
 * @author Latitude
 */
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("locale", webRequest.getLocale()
                .toString());
        errorAttributes.remove("error");
        errorAttributes.put("cause", errorAttributes.get("message"));
        errorAttributes.remove("message");
        errorAttributes.put("status", String.valueOf(errorAttributes.get("status")));
        return errorAttributes;
    }
}
