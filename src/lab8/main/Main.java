package lab8.main;

import lab8.models.*;
import lab8.utils.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        ActionLogger.log("Запуск приложения");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    removeContact();
                    break;
                case "3":
                    findByName();
                    break;
                case "4":
                    findByPhone();
                    break;
                case "5":
                    showAllContacts();
                    break;
                case "6":
                    saveToFile();
                    break;
                case "7":
                    loadFromFile();
                    break;
                case "0":
                case "8":
                    running = false;
                    System.out.println("До свидания!");
                    ActionLogger.log("Завершение работы приложения");
                    break;
                default:
                    System.out.println("Неверная команда. Попробуйте снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Телефонная книга ---");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Удалить контакт");
        System.out.println("3. Найти по имени");
        System.out.println("4. Найти по номеру");
        System.out.println("5. Показать все контакты");
        System.out.println("6. Сохранить в файл");
        System.out.println("7. Загрузить из файла");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void addContact() {
        try {
            System.out.print("Введите имя: ");
            String name = scanner.nextLine();

            System.out.print("Введите номер телефона: ");
            String phone = scanner.nextLine();

            System.out.print("Введите email (или нажмите Enter для пропуска): ");
            String email = scanner.nextLine();

            Contact contact = new Contact(name, phone, email);
            if (phoneBook.addContact(contact)) {
                System.out.println("Контакт успешно добавлен!");
            } else {
                System.out.println("Ошибка: Контакт с таким номером уже существует.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private static void removeContact() {
        System.out.print("Введите номер телефона для удаления: ");
        String phone = scanner.nextLine();

        if (phoneBook.removeContact(phone)) {
            System.out.println("Контакт удален.");
        } else {
            System.out.println("Контакт с таким номером не найден.");
        }
    }

    private static void findByName() {
        System.out.print("Введите имя (или часть имени) для поиска: ");
        String name = scanner.nextLine();

        List<Contact> found = phoneBook.findByName(name);
        if (found.isEmpty()) {
            System.out.println("Контакты не найдены.");
        } else {
            System.out.println("Найденные контакты:");
            for (Contact c : found) {
                System.out.println(c);
            }
        }
    }

    private static void findByPhone() {
        System.out.print("Введите номер телефона: ");
        String phone = scanner.nextLine();

        Contact contact = phoneBook.findByPhone(phone);
        if (contact != null) {
            System.out.println("Найден контакт: " + contact);
        } else {
            System.out.println("Контакт не найден.");
        }
    }

    private static void showAllContacts() {
        List<Contact> all = phoneBook.getAllContacts();
        if (all.isEmpty()) {
            System.out.println("Телефонная книга пуста.");
            return;
        }

        System.out.println("Список контактов:");
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i + 1) + ". " + all.get(i));
        }
    }

    private static void saveToFile() {
        System.out.print("Введите имя файла для сохранения (например, contacts.txt): ");
        String filename = scanner.nextLine();
        try {
            phoneBook.saveToFile(filename);
            System.out.println("Данные успешно сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine();
        try {
            phoneBook.loadFromFile(filename);
            System.out.println("Данные успешно загружены.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла (возможно, файл не найден): " + e.getMessage());
        }
    }
}
