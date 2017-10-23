package com.brainacademy.web.servlets;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

public class DefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        File file = new File(getServletContext().getRealPath(".") + "/static" + req.getServletPath());
        if (file.isFile() && file.exists()) {
            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
            resp.setHeader("Content-Type", URLConnection.guessContentTypeFromName(file.getName()));
        } else {
            resp.sendError(404);
        }
    }
}
