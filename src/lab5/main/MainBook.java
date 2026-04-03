package lab5.main;

import lab5.models.Book;

public class MainBook {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("Война и мир", "Л.Н. Толстой", 1869, 1225, "Эксмо", true);
        books[1] = new Book("Мастер и Маргарита", "М.А. Булгаков", 1967, 480, true); // Без издательства
        books[2] = new Book("Основы Java", "Иван Иванов", 1400, -5, "IT Press", true); // Некорректные данные

        System.out.println("--- Список книг ---");
        for (Book book : books) {
            book.printInfo();
            System.out.println("Старая ли книга (старше 50 лет)? " + book.isOld());
            System.out.println("-");
        }

        System.out.println("--- Проверка выдачи и возврата ---");
        books[0].borrow(); // Берем
        books[0].borrow(); // Пытаемся взять еще раз
        books[0].returnBook(); // Возвращаем
    }
}
