/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.web.controller.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class TMCUserDetails extends User {
    private Long   tmcId;

    private String tmcCode;

    private String tmcName;

    private Long   tmcCredentialId;
    private Long   tmcEmployeeId;
    private String tmcEmployeeName;

    private String workNo;

    public TMCUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public TMCUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Long getTmcId() {
        return tmcId;
    }

    public void setTmcId(Long tmcId) {
        this.tmcId = tmcId;
    }

    public String getTmcCode() {
        return tmcCode;
    }

    public void setTmcCode(String tmcCode) {
        this.tmcCode = tmcCode;
    }

    public String getTmcName() {
        return tmcName;
    }

    public void setTmcName(String tmcName) {
        this.tmcName = tmcName;
    }

    public Long getTmcEmployeeId() {
        return tmcEmployeeId;
    }

    public void setTmcEmployeeId(Long tmcEmployeeId) {
        this.tmcEmployeeId = tmcEmployeeId;
    }

    public String getTmcEmployeeName() {
        return tmcEmployeeName;
    }

    public void setTmcEmployeeName(String tmcEmployeeName) {
        this.tmcEmployeeName = tmcEmployeeName;
    }

    public Long getTmcCredentialId() {
        return tmcCredentialId;
    }

    public void setTmcCredentialId(Long tmcCredentialId) {
        this.tmcCredentialId = tmcCredentialId;
    }

    /**
     * Getter method for property <tt>workNo</tt>.
     * 
     * @return property value of workNo
     */
    public String getWorkNo() {
        return workNo;
    }

    /**
     * Setter method for property <tt>workNo</tt>.
     * 
     * @param workNo value to be assigned to property workNo
     */
    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

}
