package lab7.task1.models;

public class Book {
    private static int totalBooks;

    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int borrowedCopies;

    private static String generateIsbn() {
        return "ISBN-" + (totalBooks + 1);
    }

    public Book(String title, String author){
        this(title,author,1);
    }

    public Book (String title, String author, int totalCopies){
        this.title = title;
        this.author = author;
        setTotalCopies(totalCopies);
        this.isbn = generateIsbn();
        this.borrowedCopies = 0;

        totalBooks++;
    }

    public Book(Book other){
        this.title = other.title;
        this.author = other.author;
        this.totalCopies = other.totalCopies;
        this.isbn = other.isbn;
        this.borrowedCopies = other.borrowedCopies;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }

    public int getTotalCopies() { return totalCopies; }
    public void setTotalCopies(int totalCopies) {
        if (totalCopies >= 0) {
            this.totalCopies = totalCopies;
        } else {
            System.out.println("Ошибка: Количество копий не может быть отрицательным.");
        }
    }

    public int getBorrowedCopies() { return borrowedCopies; }

    public boolean isAvailable() {
        return (totalCopies - borrowedCopies) > 0;
    }

    public void borrowCopy(){
        if (isAvailable()) {
            borrowedCopies++;
            System.out.println("Вы взяли копию книги: " + title);
        } else {
            System.out.println("Извините, свободных экземпляров книги '" + title + "' нет.");
        }
    }

    public void returnCopy(){
        if (borrowedCopies > 0) {
            borrowedCopies--;
            System.out.println("Вы вернули копию книги: " + title);
        } else {
            System.out.println("Ошибка: Все копии книги '" + title + "' уже на месте.");
        }
    }

    public void printInfo() {
        System.out.printf("Книга: %s,  Автор: %s", title, author);
        System.out.printf("ID: " + isbn + "  Доступно: " + (totalCopies - borrowedCopies) + "/" + totalCopies);
        System.out.println("-----------------------------------");
    }
}
