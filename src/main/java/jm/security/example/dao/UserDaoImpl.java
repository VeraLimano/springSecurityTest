package jm.security.example.dao;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    List<User> userList;
    private int count;

    {
        userList = new ArrayList<User>();
        userList.add(new User(1, "test", "test", Collections.singleton(new Role(1, "ROLE_USER"))));
        userList.add(new User(2, "test1", "test1", Collections.singleton(new Role(2, "ROLE_USER"))));
        userList.add(new User(3, "test2", "test2", Collections.singleton(new Role(3, "ROLE_ADMIN"))));
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        for (User user1 : userList) {
            if (user1.getName().equals(name))
                user = user1;
        }
        return user;
    }

        public List<User> index() {
        return userList;
    }

    public Object show(int id) {
        User user1 = null;
        for (User user : userList) {
            if (user.getId() == id)
                user1 = user;
        }
        return user1;
    }

    public void save(User user){
        user.setId( ++count);
        userList.add(user);
    }

    public void update(int id, User updateUser) {
        User userChange = (User) show(id);
        userChange.setName(updateUser.getName());
    }

    public void delete(int id) {
        userList.removeIf(p -> p.getId() == id);
    }
}

