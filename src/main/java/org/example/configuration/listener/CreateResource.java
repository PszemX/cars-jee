package org.example.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

@WebListener
public class CreateResource implements ServletContextListener {
    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent event) {
        // Path avatarDirectory = Paths.get(getClass().getClassLoader().getResource("avatar").toURI());
        Path avatarDirectory = Paths.get(event.getServletContext().getInitParameter("avatarDirectory"));
        event.getServletContext().setAttribute("avatarDirectory", avatarDirectory);
    }
}