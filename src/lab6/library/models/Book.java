package lab6.library.models;

public class Book {
    public static int totalBooks = 0;

    private String isbn;
    private String title;
    private String author;
    private int year;
    private boolean isAvailable;
    private int borrowCount;

    public Book(String isbn, String title, String author, int year) {
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
        setYear(year);
        this.isAvailable = true;
        this.borrowCount = 0;
        totalBooks++;
    }

    // Геттеры и сеттеры с базовой валидацией
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) {
        if (isbn != null) this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) this.title = title;
        else throw new IllegalArgumentException("Название не может быть пустым.");
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public boolean isAvailable() { return isAvailable; }
    public int getBorrowCount() { return borrowCount; }

    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            borrowCount++;
        } else {
            System.out.println("Книга '" + title + "' сейчас недоступна.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }
}
