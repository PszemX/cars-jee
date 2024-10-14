package org.example.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.utils.CloningUtility;
import org.example.datastore.DataStore;

import java.nio.file.Path;
import java.nio.file.Paths;

@WebListener
public class CreateDataSource implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Path avatarDirectory = Paths.get(event.getServletContext().getInitParameter("avatarDirectory"));
        event.getServletContext().setAttribute("datasource",new DataStore(new CloningUtility(),avatarDirectory));
    }
}
