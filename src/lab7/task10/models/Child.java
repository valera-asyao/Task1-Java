package lab7.task10.models;

public class Child extends Parent {
    private static String childStaticField = initStatic("Child");
    private String childField = initInstance("Child");

    static { System.out.println("1. Статический блок Child"); }
    { System.out.println("5. Нестатический блок Child"); }

    public Child() { System.out.println("6. Конструктор Child"); }

    private static String initStatic(String name) {
        System.out.println("0. Статическое поле " + name);
        return "static";
    }

    private String initInstance(String name) {
        System.out.println("2. Поле экземпляра " + name);
        return "instance";
    }
}
