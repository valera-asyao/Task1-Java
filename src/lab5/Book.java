package lab5;

import java.time.Year;

public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private String publisher;
    private boolean isAvailable;

    public Book(String title, String author, int year, int pages, String publisher, boolean isAvailable) {
        this.title = title;
        this.author = author;
        setYear(year); // Используем сеттер для проверки
        setPages(pages); // Используем сеттер для проверки
        this.publisher = publisher;
        this.isAvailable = isAvailable;
    }


    public Book(String title, String author, int year, int pages, boolean isAvailable) {
        this(title, author, year, pages, "Неизвестно", isAvailable);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) {
        if (year >= 1450) {
            this.year = year;
        } else {
            System.out.println("Ошибка: Год издания не может быть меньше 1450. Установлено значение по умолчанию (1450).");
            this.year = 1450;
        }
    }

    public int getPages() { return pages; }
    public void setPages(int pages) {
        if (pages > 0) {
            this.pages = pages;
        } else {
            System.out.println("Ошибка: Количество страниц должно быть больше 0. Установлено 1.");
            this.pages = 1;
        }
    }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }


    public void printInfo() {
        System.out.println("Книга: '" + title + "', Автор: " + author + ", Год: " + year +
                ", Страниц: " + pages + ", Издательство: " + publisher +
                ", Доступна: " + (isAvailable ? "Да" : "Нет"));
    }

    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Вы взяли книгу: " + title);
        } else {
            System.out.println("Книга '" + title + "' уже выдана.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Вы вернули книгу: " + title);
        } else {
            System.out.println("Книга '" + title + "' и так находится в библиотеке.");
        }
    }

    public boolean isOld() {
        int currentYear = Year.now().getValue();
        return (currentYear - this.year) > 50;
    }
}
