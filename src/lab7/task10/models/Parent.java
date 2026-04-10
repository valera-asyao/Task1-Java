package lab7.task10.models;

class Parent {
    private static String parentStaticField = initStatic("Parent");
    private String parentField = initInstance("Parent");

    static { System.out.println("1. Статический блок Parent"); }
    { System.out.println("3. Нестатический блок Parent"); }

    public Parent() { System.out.println("4. Конструктор Parent"); }

    private static String initStatic(String name) {
        System.out.println("0. Статическое поле " + name);
        return "static";
    }

    private String initInstance(String name) {
        System.out.println("2. Поле экземпляра " + name);
        return "instance";
    }
}

