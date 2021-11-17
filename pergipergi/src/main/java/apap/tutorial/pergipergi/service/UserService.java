package apap.tutorial.pergipergi.service;

import java.util.List;

import apap.tutorial.pergipergi.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel findByUsername(String username);
    void deleteUser(UserModel user);
    boolean isMatch(String newPassword, String oldPassword);
    void setPassword(UserModel user, String newPassword);
}
