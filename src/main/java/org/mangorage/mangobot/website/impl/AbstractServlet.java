package org.mangorage.mangobot.website.impl;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

public abstract class AbstractServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        System.out.println(STR."\{getServletInfo()} initialized!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public String getServletInfo() {
        return getClass().getName();
    }

    @Override
    public void destroy() {
        System.out.println(STR."\{getServletInfo()} destroyed!");
    }
}
