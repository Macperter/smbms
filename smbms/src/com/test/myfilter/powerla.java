package com.test.myfilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class powerla implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest res = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = res.getSession();
        if (session.getAttribute("vist")!=null )
            filterChain.doFilter(servletRequest,servletResponse);
        else{

            String url = res.getRequestURL().toString();
            String uri = res.getRequestURI();

            url = url.substring(0,url.indexOf(uri));
            System.out.println(url);
            resp.sendRedirect(url+"/smbms_war_exploded/login.html");
        }



    }
}
