package lab7.task4.main;
import lab7.task4.models.ImmutableRectangle;
public class Main {
    public static void main(String[] args) {
        try {
            ImmutableRectangle rect1 = new ImmutableRectangle(10.0, 5.0);
            System.out.println("Исходный прямоугольник");
            rect1.printInfo("Rect1");

            ImmutableRectangle rect2 = rect1.withWidth(20.0);

            System.out.println("\nПосле вызова withWidth(20.0)");
            rect2.printInfo("Rect2 (новый)");
            rect1.printInfo("Rect1 (старый, не изменился)");

            ImmutableRectangle rect3 = rect1.withWidth(15).withHeight(15);
            System.out.println("\nПрямоугольник после цепочки изменений");
            rect3.printInfo("Rect3");

            /*System.out.println("\nТест некорректных данных");
            ImmutableRectangle badRect = new ImmutableRectangle(-1, 5);
*/
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
