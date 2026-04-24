package lab8.models;

import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email){
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }
    
    String Name(){
        return name;
    }

    String PhoneNumber(){
        return phoneNumber;
    }

    String Email(){
        return email;
    }

    void setName(String name) {
        if(name == null){
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name;
    }

    void setPhoneNumber(String phoneNumber){
        if(phoneNumber == null)
            throw new IllegalArgumentException("Номер не может быть пустой");
        setName(name);
        this.phoneNumber = phoneNumber;
    }

    void setEmail(String email){
        if(email == null){
            throw new IllegalArgumentException("Email не может быть пустой");
        }
    }

@Override
public String toString() {
return "Контакт [Имя: " + name + ", номер " + phoneNumber + " email: " + email + "]\n";
}
    
@Override
public boolean equals(Object o){
    if (this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    Contact user = (Contact) o;
    return Objects.equals(phoneNumber, user.phoneNumber);
}

@Override
public int compareTo(Contact o){
    return this.name.compareToIgnoreCase(o.name);
}

@Override
public int hashCode(){
    return Objects.hash(name, phoneNumber, email);
}
}