package lab6.store.models;

public class Category {
    public static int totalCategories = 0;
    private String categoryName;
    private String description;

    public Category(String categoryName, String description) {
        setCategoryName(categoryName);
        this.description = description;
        totalCategories++;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) this.categoryName = categoryName;
        else throw new IllegalArgumentException("Имя категории не может быть пустым.");
    }
    public String getCategoryName() { return categoryName; }
}
