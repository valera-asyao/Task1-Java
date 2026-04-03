package lab6.geometry.services;
import lab6.geometry.models.Circle;
import lab6.geometry.models.Rectangle;
import lab6.geometry.models.Triangle;
import lab6.geometry.utils.MathUtils;


public class ShapeService {
    public static Circle createCircle(double radius) {
        if (radius <= 0) throw new IllegalArgumentException("Радиус должен быть больше 0");
        // Используем трюк: так как классы в другом пакете, конструкторы должны быть public,
        // Либо ShapeService должен быть в пакете models.
        // В рамках задания сделаем метод-фабрику, который проверяет логику.
        return new Circle(radius); // Примечание: для этого конструктор Circle должен быть public.
    }

    public static Rectangle createRectangle(double width, double height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Стороны должны быть больше 0");
        return new Rectangle(width, height);
    }

    public static Triangle createTriangle(double a, double b, double c) {
        if (!MathUtils.isValidTriangle(a, b, c)) {
            throw new IllegalArgumentException("Такой треугольник не существует!");
        }
        return new Triangle(a, b, c);
    }
}
