package lab6.library.services;

import lab6.library.models.Book;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    public static List<Book> getAvailableBooks(List<Book> books) {
        List<Book> available = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) available.add(b);
        }
        return available;
    }

    public static Book getMostPopularBook(List<Book> books) {
        if (books == null || books.isEmpty()) return null;
        Book popular = books.get(0);
        for (Book b : books) {
            if (b.getBorrowCount() > popular.getBorrowCount()) {
                popular = b;
            }
        }
        return popular;
    }

    public static List<Book> searchByAuthor(List<Book> books, String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                result.add(b);
            }
        }
        return result;
    }
}
