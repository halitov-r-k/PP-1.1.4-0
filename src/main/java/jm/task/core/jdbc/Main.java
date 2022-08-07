package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

     /*
     В методе main класса Main должны происходить следующие операции:
        Создание таблицы User(ов)
        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль (User с именем – name добавлен в базу данных)
        Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        Очистка таблицы User(ов)
        Удаление таблицы
      */
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Name1", "LastName1", (byte) 91);
        User user2 = new User("Name2", "LastName2", (byte) 92);
        User user3 = new User("Name3", "LastName3", (byte) 93);
        User user4 = new User("Name4", "LastName4", (byte) 94);

        userService.saveUser (user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser (user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser (user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser (user4.getName(), user4.getLastName(), user4.getAge());

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.closeConnection();
    }
}
