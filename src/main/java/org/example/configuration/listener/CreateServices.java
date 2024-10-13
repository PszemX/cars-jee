package org.example.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.datastore.DataStore;
import org.example.user.avatar.repository.AvatarRepository;
import org.example.user.avatar.service.AvatarService;
import org.example.user.repository.api.UserRepository;
import org.example.user.repository.memory.UserInMemoryRepository;
import org.example.user.service.UserService;

@WebListener
public class CreateServices implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataStore = (DataStore) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserInMemoryRepository(dataStore);
        AvatarRepository avatarRepository = new AvatarRepository(dataStore);

        event.getServletContext().setAttribute("userService", new UserService(userRepository));
        event.getServletContext().setAttribute("avatarService", new AvatarService(avatarRepository));
    }
}