package jm.security.example.dao;

import jm.security.example.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);

    public List<User> index();

    public Object show(int id);

    public void save(User user);

    public void update(int id, User updateUser);

    public void delete(int id);
}
