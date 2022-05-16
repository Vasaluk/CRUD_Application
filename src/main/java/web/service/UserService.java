package web.service;

import web.model.User;
import web.repository.UserRepository;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public List<User> listAll();

    public User getUser(Long id);

    public void deleteUser(Long id);
}
