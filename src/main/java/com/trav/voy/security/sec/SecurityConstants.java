/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.sec;

/**
 *
 * @author MEDION
 */
public class SecurityConstants {

    public static final String SECRET = "aaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofraaronnjoyayahoofr";
//    public static final long EXPIRATION_TIME = 10*24*3600*1000;  // 10jours
    public static final long EXPIRATION_TIME = 86_400_000;  // 1 jour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
