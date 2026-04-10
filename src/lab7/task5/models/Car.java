package lab7.task5.models;

import java.util.Set;
import java.util.HashSet;

public class Car {
    private String model;
    private String color;
    private int year;

    private static Set<String> availableColors;

    static {
        availableColors = new HashSet<>();
        availableColors.add("Red");
        availableColors.add("Blue");
        availableColors.add("Black");
        availableColors.add("White");
        availableColors.add("Silver");
        System.out.println("--- Список доступных цветов инициализирован ---");
    }

    public Car (String model , String color , int year){
        if(!availableColors.contains(color))
            throw new IllegalArgumentException("Такого цвета нет в нашем списке");

        this.model = model;
        this.color = color;
        this.year = year;
    }

    public static void addNewColor(String color) {
        if (color != null && !color.isEmpty()) {
            availableColors.add(color);
            System.out.println("Цвет '" + color + "' успешно добавлен в палитру.");
        }
    }

    public static Set<String> getAvailableColors() {
        return new HashSet<>(availableColors); // Возвращаем копию для безопасности
    }

    public void changeColor(String newColor) {
        if (availableColors.contains(newColor)) {
            this.color = newColor;
            System.out.println("Цвет автомобиля " + model + " изменен на " + newColor);
        } else {
            throw new IllegalArgumentException("Невозможно перекрасить: цвет '" + newColor + "' отсутствует в палитре.");
        }
    }

    public void printInfo() {
        System.out.println("Автомобиль: " + model + " | Цвет: " + color + " | Год: " + year);
    }

}
