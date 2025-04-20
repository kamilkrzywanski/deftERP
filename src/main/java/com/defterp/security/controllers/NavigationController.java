package com.defterp.security.controllers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */


@Named(value = "navigationBean")
@SessionScoped
public class NavigationController implements Serializable {

    private static final long serialVersionUID = 1L;


    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }


    public String toLogin() {
        return "/login.xhtml";
    }


    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }


    public String toInfo() {
        return "/info.xhtml";
    }


    public String redirectToWelcome() {
        return "/sc/dashboard.xhtml?faces-redirect=true";
    }


    public String toWelcome() {
        return "/sc/dashboard.xhtml";
    }

}
