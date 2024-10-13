package org.example.user.avatar.controller.api;

import java.io.InputStream;
import java.util.UUID;

public interface AvatarController {
    byte[] getAvatar(UUID uuid);
    void updateAvatar(UUID uuid, InputStream avatar);
    void deleteAvatar(UUID uuid);
    void createAvatar(UUID uuid,byte[] avatar);
}
