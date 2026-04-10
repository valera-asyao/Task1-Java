package lab7.task4.models;

public class ImmutableRectangle {
    private final double width;
    private final double height;

    public ImmutableRectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Стороны прямоугольника должны быть положительными. " +
                    "Передано: " + width + "x" + height);
        }
        this.width = width;
        this.height = height;
    }

    public double Width() { return  width; }
    public double Height() { return  height; }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public ImmutableRectangle withWidth(double newWidth){
        return new ImmutableRectangle(newWidth, this.height);
    }

    public ImmutableRectangle withHeight(double newHeight){
        return new ImmutableRectangle(this.width, newHeight);
    }

    public void printInfo(String name) {
        System.out.println(name + ": [" + width + " x " + height + "]");
        System.out.println("  Площадь: " + getArea());
        System.out.println("  Периметр: " + getPerimeter());
    }
}
