package lab6.geometry.services;
import java.util.List;

public class AreaCalculator {
    public static double sumAreas(List<Object> shapes) {
        double sum = 0;
        for (Object shape : shapes) {
            if (shape instanceof lab6.geometry.models.Circle) sum += ((lab6.geometry.models.Circle) shape).getArea();
            else if (shape instanceof lab6.geometry.models.Rectangle) sum += ((lab6.geometry.models.Rectangle) shape).getArea();
            else if (shape instanceof lab6.geometry.models.Triangle) sum += ((lab6.geometry.models.Triangle) shape).getArea();
        }
        return sum;
    }
}
