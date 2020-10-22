package com.example.listadapter;

public class StudentModel {
    private String Nume;
    private String Prenume;

    public StudentModel(String nume, String prenume) {
        Nume = nume;
        Prenume = prenume;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }
}
