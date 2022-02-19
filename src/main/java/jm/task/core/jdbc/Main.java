package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Иван", "Иванов", (byte) 33);
        user.saveUser("Наташа", "Ростова", (byte) 22);
        user.saveUser("Никита", "Ларин", (byte) 17);
        user.saveUser("Андрей", "Васильев", (byte) 55);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
