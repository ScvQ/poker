package test.controller;

import test.service.IUserService;
import test.service.impl.UserService;

public class UserController {

    private IUserService userService = new UserService();

    public void login(String username, String password) {

    }

}
