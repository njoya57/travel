/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.config;


  import org.springframework.http.HttpStatus;


/**
 * Simple static methods to be called at the start of your own methods to verify correct arguments and state. If the Precondition fails, an {@link HttpStatus} code is thrown
 */
public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    // API

    /**
     * Check if some value was found, otherwise throw exception.
     * 
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static void checkFound(final boolean expression) {
        if (!expression) {
            throw new MyResourceNotFoundException();
        }
    }

    /**
     * Check if some value was found, otherwise throw exception.
     * 
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }

        return resource;
    }

}