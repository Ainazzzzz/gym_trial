package peaksoft.service.implService;

import peaksoft.dao.implDao.UserDaoImpl;
import peaksoft.entity.User;
import peaksoft.service.UserService;

import java.util.concurrent.atomic.AtomicBoolean;

public class UserServiceImpl implements UserService {
    private final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void save(User user) {
        userDao.save(user);
    }


       public boolean usernameExists(String username) {
        AtomicBoolean exists = new AtomicBoolean(false);
        userDao.getAll().forEach(user -> {
            if (user.getUsername().equals(username)) {
                exists.set(true);
            }
        });
        return exists.get();
    }
}
