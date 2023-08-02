package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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