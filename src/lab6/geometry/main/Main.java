package lab6.geometry.main;
import lab6.geometry.services.ShapeService;
import lab6.geometry.services.AreaCalculator;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            var c1 = ShapeService.createCircle(5);
            var r1 = ShapeService.createRectangle(4, 5);
            var t1 = ShapeService.createTriangle(3, 4, 5);

            System.out.println("Площадь круга: " + c1.getArea());

            double totalArea = AreaCalculator.sumAreas(Arrays.asList(c1, r1, t1));
            System.out.println("Суммарная площадь всех фигур: " + totalArea);

            // Проверка валидации:
            ShapeService.createTriangle(1, 1, 10); // Вызовет исключение

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания фигуры: " + e.getMessage());
        }
    }
}
