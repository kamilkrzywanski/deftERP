package com.defterp.security.filters;

import com.defterp.security.controllers.UserSessionController;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@WebFilter(filterName = "isLogedInFilter")
public class IsLogedInFilter implements Filter {

    @Inject
    UserSessionController userSessionController;
    String loginPage = "/sc/loginPage.xhtml";
    String homePage = "/sc/dashboard.xhtml";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        LoginController loginController = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController");
        System.out.println("LoginFilter called");
        System.out.println("is Session Valid: " + isSessionValid((HttpServletRequest) request));
        System.out.println("loginController is null: " + (userSessionController == null));
//        System.out.println("New session: " + ((HttpServletRequest) request).getSession(false).isNew());
        System.out.println("Session doesnt Exist: " + (((HttpServletRequest) request).getSession(false) == null));

        System.out.println("timeout attribute: " + ((HttpServletRequest) request).getAttribute("timeout"));

        String url = ((HttpServletRequest) request).getRequestURL().toString();

        String facesRequest = ((HttpServletRequest) request).getHeader("Faces-Request");
        if (facesRequest == null) {
            facesRequest = "";
        }

//        if (((loginController == null || !loginController.isLoggedIn()) && isSessionValid((HttpServletRequest) request)) || (((HttpServletRequest) request).getSession(false).isNew() && loginController == null && !isSessionValid((HttpServletRequest) request))) {
//        if (((loginController == null || !loginController.isLoggedIn()) && !((HttpServletRequest) request).getSession().isNew())) {
        if ((userSessionController == null || !userSessionController.isLoggedIn()) && !facesRequest.equals("partial/ajax") && !url.contains(loginPage)) {

            System.out.println("Redirect");
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletRequest) request).getRequestDispatcher(loginPage).forward(request, response);
        } else if (userSessionController != null && userSessionController.isLoggedIn() && !facesRequest.equals("partial/ajax") && url.contains(loginPage)) {
            System.out.println("Redirect");
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + homePage);
        }

        chain.doFilter(request, response);
    }

    private boolean isSessionValid(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestedSessionId() != null;
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
