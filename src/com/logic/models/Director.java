package com.logic.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Director implements Serializable, Comparable<Director> {

    private String name, surname;
    private LocalDate dateOfBirth;
    private Locale nationality;

    public Director() {
    }

    public Director(String name, String surname, LocalDate dateOfBirth, Locale nationality) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Locale getNationality() {
        return nationality;
    }

    public void setNationality(Locale nationality) {
        this.nationality = nationality;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Director other = (Director) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return Objects.equals(this.nationality, other.nationality);
    }

    @Override
    public String toString() {
        return "Director{" + "name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", nationality=" + nationality + '}';
    }

    @Override
    public int compareTo(Director o) {
        if (this.name.compareToIgnoreCase(o.name) != 0) {
            return this.name.compareToIgnoreCase(o.name);
        }
        if (this.surname.compareToIgnoreCase(o.surname) != 0) {
            return this.surname.compareToIgnoreCase(o.surname);
        }
        return this.getDateOfBirth().getYear() - o.getDateOfBirth().getYear();
    }

}
