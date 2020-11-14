/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class SingleResourceRetrievedEvent extends ApplicationEvent {

    private final HttpServletResponse response;

    public SingleResourceRetrievedEvent(final Object source, final HttpServletResponse response) {
        super(source);

        this.response = response;
    }

    // API
    public HttpServletResponse getResponse() {
        return response;
    }

}
