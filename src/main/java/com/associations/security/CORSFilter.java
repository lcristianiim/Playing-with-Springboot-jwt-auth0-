package com.associations.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by admin on 16.07.2017.
 */
public class CORSFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    protected Boolean isOptionsRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("Request " + request.getMethod());

        HttpServletResponse response = (HttpServletResponse) res;
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, PATCH, DELETE");
        response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");

        // Just ACCEPT and REPLY OK if OPTIONS
        if (isOptionsRequest(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, res);
    }


    public void destroy() {

    }
}