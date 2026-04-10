package lab7.task1.main;
import lab7.task1.models.Book;

public class MainBook {
    public static void main(String[] args) {
        Book book1 = new Book("Война и Мир", "Л.Н.Толстой");
        Book book2 = new Book("Преступление и наказание", "Ф.М.Достоевский", 10);
        Book book3 = new Book(book2);

        System.out.println("Создано уникальных книг: " + Book.getTotalBooks());
        System.out.println();

        book2.printInfo();
        book2.borrowCopy();
        book2.borrowCopy();

        System.out.println("Состояние после выдачи 2-х копий:");
        book2.printInfo();

        System.out.println("Информация о скопированном объекте (ISBN должен совпасть):");
        book3.printInfo();

        Book book4 = new Book("Java: Руководство для начинающих", "Г. Шилдт", 3);
        book4.printInfo();

        System.out.println("Финальное количество созданных уникальных книг: " + Book.getTotalBooks());

        // Проверка возврата
        book2.returnCopy();
        System.out.println("Доступно ли book2? " + (book2.isAvailable() ? "Да" : "Нет"));
    }
}
