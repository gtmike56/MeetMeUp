package com.example.meetmeup;

import java.util.ArrayList;
import java.util.List;

public class Group {
    //private int ID;
    private String name;
    private ArrayList<String> emails;
    private String documentId;

    public Group(){
        emails = new ArrayList<String>();
    }
    public Group(String name){
        this.name = name;
        emails = new ArrayList<String>();
    }
    public Group(String name, String documentId) {
        this.name = name;
        this.documentId = documentId;
        emails = new ArrayList<String>();
    }


    public void addMember(String email){
        emails.add(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
