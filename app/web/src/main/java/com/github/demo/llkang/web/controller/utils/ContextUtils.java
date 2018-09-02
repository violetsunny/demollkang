/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.web.controller.utils;

import com.github.demo.llkang.web.controller.security.TMCUserDetails;
import com.google.common.collect.Sets;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author dyb37716
 * @version $Id: ContextUtils, v 0.1 2016/10/28 15:06 dyb37716 Exp $
 */
public class ContextUtils {
    public ContextUtils(){
    }

    public static TMCUserDetails getCurrentUser(){
        TMCUserDetails user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof TMCUserDetails) {
                    user = (TMCUserDetails) principal;
                }
            }
        }

        return user;
    }

    public static String getCurrentUserName(){
        TMCUserDetails curUser = getCurrentUser();
        return curUser != null ? curUser.getTmcEmployeeName() : "";
    }

    public static boolean allGranted(String[] checkForAuths){
        Set userAuths = getUserAuthorities();
        String[] var2 = checkForAuths;
        int var3 = checkForAuths.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String auth = var2[var4];
            if (!userAuths.contains(auth)) {
                return false;
            }
        }

        return true;
    }

    public static boolean anyGranted(String[] checkForAuths){
        Set userAuths = getUserAuthorities();
        String[] var2 = checkForAuths;
        int var3 = checkForAuths.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String auth = var2[var4];
            if (userAuths.contains(auth)) {
                return true;
            }
        }

        return false;
    }

    public static boolean noneGranted(String[] checkForAuths){
        Set userAuths = getUserAuthorities();
        String[] var2 = checkForAuths;
        int var3 = checkForAuths.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String auth = var2[var4];
            if (userAuths.contains(auth)) {
                return false;
            }
        }

        return true;
    }

    private static Set<String> getUserAuthorities(){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashSet roles = Sets.newHashSet();
        if (obj instanceof UserDetails) {
            Collection gas = ((UserDetails) obj).getAuthorities();
            Iterator var3 = gas.iterator();

            while (var3.hasNext()) {
                GrantedAuthority ga = (GrantedAuthority) var3.next();
                roles.add(ga.getAuthority());
            }
        }

        return roles;
    }
}

