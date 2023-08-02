package org.example;

import jakarta.persistence.*;
import org.example.entity.Users;
import org.hibernate.query.TypedTupleTransformer;

import java.util.List;
import java.util.Scanner;

public class Main {
    //private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

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
            System.out.println("Не правильно выбрали");
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
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static void registration() {
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            System.out.println("Введите логин: ");

        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}