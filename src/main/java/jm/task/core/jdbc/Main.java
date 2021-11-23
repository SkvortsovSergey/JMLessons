package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main (String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasia", "Pupkin", (byte) 25);
        userService.saveUser("Petia", "Pupkin", (byte) 30);
        userService.saveUser("Vasilisa", "Otrighkina", (byte) 19);
        userService.saveUser("Ludmila", "Zaebuha", (byte) 45);
       userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}