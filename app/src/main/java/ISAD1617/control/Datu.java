package ISAD1617.control;

import javafx.scene.image.Image;

public class Datu {
    private String firstname;
    private String lastname;
    private String sport;
    private int numyears;
    private boolean vegetarian;
    private Image argazkia;


    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getNumyears() {
        return numyears;
    }

    public void setNumyears(int numyears) {
        this.numyears = numyears;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Image getArgazkia() {
        return argazkia;
    }

    public void setArgazkia(Image argazkia) {
        this.argazkia = argazkia;
    }
}
