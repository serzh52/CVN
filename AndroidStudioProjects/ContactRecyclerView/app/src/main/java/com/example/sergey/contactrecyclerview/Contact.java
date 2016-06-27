package com.example.sergey.contactrecyclerview;

/**
 * Created by Sergey on 27.06.2016.
 */
public class Contact {

    public static final Contact[] CONTACTS = new Contact[] {
            new Contact("Петя","8012345679"),
            new Contact("Коля", "8987654321"),
            new Contact("Вася","8923456789"),
            new Contact("Аня","8987654321"),
            new Contact("юля","891235813"),
            new Contact("Федор","8923456789"),
            new Contact("Тётя мотя","891235813"),
            new Contact("Без регистрации и смс","8987654321"),
    };

    private final String mName, mPhone;
    Contact(String name, String phone) {
        mName = name; mPhone = phone;
    }
    public static Contact getItem(int id) {
        for (Contact item : CONTACTS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public int getId() {
        return mName.hashCode() + mPhone.hashCode();
    }
    public static enum Field {
        NAME, PHONE
    }
    public String get(Field f) {
        switch (f) {
            case PHONE: return mPhone;
            case NAME: default: return mName;
        }
    }
}
