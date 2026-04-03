package lab6.library.main;
import lab6.library.models.*;
import lab6.library.services.LibraryService;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("111", "1984", "Джордж Оруэлл", 1949);
        Book b2 = new Book("222", "Мастер и Маргарита", "Михаил Булгаков", 1967);
        Book b3 = new Book("333", "Скотный двор", "Джордж Оруэлл", 1945);
        List<Book> libraryBooks = Arrays.asList(b1, b2, b3);

        Reader reader1 = new Reader("Иван Иванов", "R001");

        reader1.borrowBook(b1);
        reader1.borrowBook(b2);
        reader1.returnBook(b1);

        // Другой читатель берет b1
        Reader reader2 = new Reader("Мария Смирнова", "R002");
        reader2.borrowBook(b1);

        System.out.println("\n--- Статистика Библиотеки ---");
        System.out.println("Всего книг: " + Book.totalBooks);
        System.out.println("Доступные книги: " + LibraryService.getAvailableBooks(libraryBooks).size());

        Book pop = LibraryService.getMostPopularBook(libraryBooks);
        System.out.println("Самая популярная книга: " + (pop != null ? pop.getTitle() : "Нет данных") + " (взята " + pop.getBorrowCount() + " раз)");

        System.out.println("Книги Оруэлла: " + LibraryService.searchByAuthor(libraryBooks, "Джордж Оруэлл").size());
    }
}