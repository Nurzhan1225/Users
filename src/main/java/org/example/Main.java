package org.example;

import jakarta.persistence.*;
import org.example.entity.City;
import org.example.entity.Users;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Город (индекс, название)
        // Пользователи (город, логин, пароль, имя, фамилия)
        // - Авторизация[1]
        // - Регистрация[2]
        //Выберите действие:
        //--- Авторизация ---
        // Введите логин:
        // Введите пароль:
        // Город:
        //-Индекс:
        //-Название:
        // Имя:
        // Фамилия:
        System.out.println("- Авторизация [1]\n" +
                "- Регистрация [2]\n" +
                //"- Завершить процесс [3]\n" +
                "Выберите действие:");
        Integer AR = Integer.parseInt(scanner.nextLine());
        if (AR == 1) {
            authorization();
        } else if (AR == 2) {
            registration();
        } else {
            System.out.println("Ошибка: не правильно выбрали");
        }
    }

    public static void authorization() {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();
            TypedQuery<Users> usersTypedQuery = manager.createQuery(
                    "select u from Users u where u.login = ?1 and u.password = ?2", Users.class
            );
            usersTypedQuery.setParameter(1, login);
            usersTypedQuery.setParameter(2, password);
            try {
                Users users = usersTypedQuery.getSingleResult();
                System.out.println(users);
            } catch (NoResultException e) {
                System.out.println("Не правильный логин или пароль");
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void registration() {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            System.out.println("Введите Имя: ");
            String name = scanner.nextLine();
            System.out.println("Введите Фамилию: ");
            String surname = scanner.nextLine();
            System.out.println("Введите логин: ");
            String login = scanner.nextLine();
            System.out.println("Введите пароль: ");
            String password = scanner.nextLine();

            TypedQuery<City> cityTypedQuery = manager.createQuery(
                    "select c from City c order by c.id", City.class
            );
            List<City> cities = cityTypedQuery.getResultList();
            System.out.println("Новый город: [0]");
            for (int i = 0; i < cities.size(); i++) {
                System.out.println(cities.get(i).getName() + " [" + (i + 1) + "]");
            }
            System.out.println("Выберите город: ");
            int cityID = Integer.parseInt(scanner.nextLine());
            City city = new City();
            if (cityID == 0) {
                System.out.println("Введите город: ");
                String newCity = scanner.nextLine();
                System.out.println("Введите индекс " + newCity + ": ");
                Integer index = Integer.parseInt(scanner.nextLine());
                city.setName(newCity);
                city.setIndex(index);
                manager.persist(city);
            } else if (cityID <= cities.size()) {
                city = cities.get(cityID - 1);
                System.out.println(city);
            } else {
                System.out.println("Ошибка: не правильно выбрали");
            }
            Users users = new Users();
            users.setLogin(login);
            users.setPassword(password);
            users.setName(name);
            users.setSurname(surname);
            users.setCity(city);
            manager.persist(users);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}