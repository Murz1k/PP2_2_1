package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        Car toyota = new Car("Peugeot", 1);
        User user1 = new User("Anna", "Petrova", "user1@mail.ru");
        user1.setCar(toyota);
        userService.add(user1);

        Car ford = new Car("Honda", 2);
        User user2 = new User("Ivan", "Petrov", "user2@mail.ru");
        user2.setCar(ford);
        userService.add(user2);

        Car subaru = new Car("Porsche", 3);
        User user3 = new User("Lena", "Ivanova", "user3@mail.ru");
        user3.setCar(subaru);
        userService.add(user3);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUserByModelAndSeries("Peugeot", 1));
        System.out.println(userService.getUserByModelAndSeries("Honda", 2));
        System.out.println(userService.getUserByModelAndSeries("Porsche", 3));

        context.close();
    }
}
