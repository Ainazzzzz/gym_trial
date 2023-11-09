package peaksoft.dao;

import peaksoft.entity.User;

public interface UserDao {
    void save(User user);

    Iterable<User> getAll();
}
