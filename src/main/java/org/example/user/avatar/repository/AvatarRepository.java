package org.example.user.avatar.repository;

import org.example.datastore.DataStore;

import java.util.UUID;

public class AvatarRepository {
    private final DataStore dataStore;

    public AvatarRepository(DataStore dataStore){
        this.dataStore = dataStore;
    }
    public byte[] getAvatar(UUID uuid){
        return this.dataStore.getAvatar(uuid);
    }
    public void updateAvatar(UUID uuid,byte[] avatar){
        this.dataStore.updateAvatar(uuid,avatar);
    }
    public void deleteAvatar(UUID uuid){
        this.dataStore.deleteAvatar(uuid);
    }
    public void createAvatar(UUID uuid, byte[] avatar){
        this.dataStore.createAvatar(uuid,avatar);
    }


}
