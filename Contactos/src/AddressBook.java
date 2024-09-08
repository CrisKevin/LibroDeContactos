
import java.io.*;
import java.util.HashMap;

public class AddressBook {
    private HashMap<String, Contact> contacts;

    public AddressBook() {
        this.contacts = new HashMap<>();
    }
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getEmail())) {
            System.out.println("A contact with this email already exists.");
        } else {
            contacts.put(contact.getEmail(), contact);
            System.out.println("Contact added successfully.");
            storeContacts();
        }
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("The address book is empty.");
            return;
        }
        for (Contact contact : contacts.values()) {
            System.out.println(contact.print());
        }
    }

    public void searchContact(String email) {
        if (contacts.containsKey(email)) {
            System.out.println(contacts.get(email));
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void deleteContact(String email) {
        if (contacts.containsKey(email)) {
            contacts.remove(email);
            System.out.println("Contact deleted.");
            storeContacts();
        } else {
            System.out.println("No contact found with the provided email.");
        }
    }

    public void storeContacts(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"))){
            oos.writeObject(contacts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadContacts() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"))){
            contacts = (HashMap<String, Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
