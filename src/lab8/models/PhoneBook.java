package lab8.models;

import lab8.utils.*;
import java.io.*;
import java.util.*;

public class PhoneBook {
    private final List<Contact> contacts;
    // HashMap для быстрого поиска по номеру телефона O(1)
    private final Map<String, Contact> phoneIndex;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
        this.phoneIndex = new HashMap<>();
    }

    public boolean addContact(Contact contact) {
        if (phoneIndex.containsKey(contact.PhoneNumber())) {
            return false; // Контакт с таким номером уже существует
        }
        contacts.add(contact);
        phoneIndex.put(contact.PhoneNumber(), contact);
        ActionLogger.log("Добавлен контакт: " + contact.Name() + " (" + contact.PhoneNumber() + ")");
        return true;
    }

    public boolean removeContact(String phoneNumber) {
        String formattedPhone = PhoneNumberValidator.formatPhone(phoneNumber);
        Contact contact = phoneIndex.remove(formattedPhone);
        if (contact != null) {
            contacts.remove(contact);
            ActionLogger.log("Удален контакт: " + contact.Name() + " (" + formattedPhone + ")");
            return true;
        }
        return false;
    }

    public List<Contact> findByName(String nameQuery) {
        List<Contact> result = new ArrayList<>();
        String lowerCaseQuery = nameQuery.toLowerCase();
        for (Contact contact : contacts) {
            if (contact.Name().toLowerCase().contains(lowerCaseQuery)) {
                result.add(contact);
            }
        }
        ActionLogger.log("Поиск по имени: " + nameQuery + ". Найдено: " + result.size());
        return result;
    }

    public Contact findByPhone(String phoneNumber) {
        String formattedPhone = PhoneNumberValidator.formatPhone(phoneNumber);
        ActionLogger.log("Поиск по номеру: " + formattedPhone);
        return phoneIndex.get(formattedPhone);
    }

    public List<Contact> getAllContacts() {
        List<Contact> sortedContacts = new ArrayList<>(contacts);
        Collections.sort(sortedContacts);
        return sortedContacts;
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.Name() + "|" + contact.PhoneNumber() + "|" + contact.Email());
                writer.newLine();
            }
            ActionLogger.log("Контакты сохранены в файл: " + filename);
        }
    }

    public void loadFromFile(String filename) throws IOException {
        int addedCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1); // -1 чтобы сохранить пустые строки в конце (email)
                if (parts.length >= 2) {
                    try {
                        String email = parts.length == 3 ? parts[2] : "";
                        Contact contact = new Contact(parts[0], parts[1], email);
                        if (addContact(contact)) {
                            addedCount++;
                        }
                    } catch (IllegalArgumentException e) {
                        // Пропускаем некорректные строки в файле
                        System.err.println("Пропущена некорректная запись в файле: " + line);
                    }
                }
            }
            ActionLogger.log("Загружено контактов из файла " + filename + ": " + addedCount);
        }
    }
}
