package lab6.university.models;

import lab6.university.utils.StringUtils;

public class Professor {
    private String name;

    public Professor(String name) {
        setName(name);
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (StringUtils.isValidName(name)) {
            this.name = StringUtils.capitalize(name);
        } else {
            throw new IllegalArgumentException("Некорректное имя профессора.");
        }
    }
}
