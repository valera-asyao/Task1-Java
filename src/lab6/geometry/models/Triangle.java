package lab6.geometry.models;

public class Triangle {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getArea() {
        double p = getPerimeter() / 2;
        // Формула Герона
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double getPerimeter() { return a + b + c; }
}
