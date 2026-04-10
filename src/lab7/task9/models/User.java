package lab7.task9.models;

public class User {
    private final String firstName;
    private final String lastName;
    private final String email;

    private final int age;
    private final String phone;
    private final String address;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    @Override
    public String toString() {
        return String.format("User: %s %s | Email: %s | Age: %d | Phone: %s | Address: %s",
                firstName, lastName, email, age, phone, address);
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private int age = 0;
        private String phone = "N/A";
        private String address = "N/A";

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            if (firstName == null || lastName == null || email == null) {
                throw new IllegalStateException("Не заполнены обязательные поля (Имя, Фамилия, Email)");
            }
            return new User(this);
        }
    }
}