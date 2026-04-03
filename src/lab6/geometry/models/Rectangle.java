package lab6.geometry.models;

public class Rectangle {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() { return width * height; }
    public double getPerimeter() { return 2 * (width + height); }
}
