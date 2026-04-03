package lab6.geometry.models;

public class Circle {
    public static final double PI = 3.141592653589793;
    private double radius;

    // Конструктор package-private, чтобы создавать через фабрику
    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() { return radius; }
    public double getArea() { return PI * radius * radius; }
    public double getPerimeter() { return 2 * PI * radius; }
}
