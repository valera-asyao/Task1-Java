package lab8.models;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import lab8.models.*;

public class PhoneBook {
    private ArrayList<Contact> listContact;

    void addContact(Contact contact){
        for(int i = 0; i < listContact.size(); i++){
            if(listContact.get(i).equals(contact)){
                throw new IllegalArgumentException("Такой контакт уже существует");
            }
        }
        listContact.add(contact);
    }

    void removeContact(Contact contact){
        if(!listContact.contains(contact)){
            throw new IllegalArgumentException("Такого контакта не существует");
        }
        listContact.remove(contact);
    }

    ArrayList<Contact> findByName(String name){
        ArrayList<Contact> result = new ArrayList<>();
        for(int i = 0; i < listContact.size(); i++){
            if(listContact.get(i).Name().equals(name)){
                result.add(listContact.get(i));
            }
        }
        return result;
    }

    ArrayList<Contact> findByPhoneNumber(String phoneNumber){
        ArrayList<Contact> result = new ArrayList<>();
        for(int i = 0; i < listContact.size(); i++){
            if(listContact.get(i).PhoneNumber().equals(phoneNumber)){
                result.add(listContact.get(i));
            }
        }
        return result;
    }

    ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> result = new ArrayList<>(listContact);
        Collections.sort(result, (c1, c2) -> c1.Name().compareTo(c2.Name()));
        return result;
    }

    public void saveToFile(String filename){
        try(FileWriter fw = new FileWriter(filename)){
            for(Contact contact : listContact){
                fw.write(contact.Name() + "|" + contact.PhoneNumber() + "|" + contact.Email() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении контактов в файл");
        }
    }

    public void loadFromFile(String filename){
        try(BufferedReader fr = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = fr.readLine()) != null){
                    String[] parts = line.split("|");
                    if(parts.length == 3){
                        Contact contact = new Contact(parts[0], parts[1], parts[2]);
                        listContact.add(contact);
                    }
                }       
        }
        catch (IOException e) {
            System.err.println("Ошибка при чтении контактов из файла");
        }
    }
}
