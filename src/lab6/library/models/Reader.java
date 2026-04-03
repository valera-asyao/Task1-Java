package lab6.library.models;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static int totalReaders = 0;

    private String fullName;
    private String readerId;
    private List<Book> borrowedBooks;

    public Reader(String fullName, String readerId) {
        this.fullName = fullName;
        this.readerId = readerId;
        this.borrowedBooks = new ArrayList<>();
        totalReaders++;
    }

    public String getFullName() { return fullName; }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrow();
            borrowedBooks.add(book);
            System.out.println(fullName + " взял книгу: " + book.getTitle());
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println(fullName + " вернул книгу: " + book.getTitle());
        } else {
            System.out.println("У читателя нет этой книги.");
        }
    }
}
