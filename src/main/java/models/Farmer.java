package models;

import java.util.Objects;

public class Farmer {
    private String farmerName;
    private String location;
    private int quantity;
    private String breed;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return quantity == farmer.quantity &&
                id == farmer.id &&
                Objects.equals(farmerName, farmer.farmerName) &&
                Objects.equals(location, farmer.location) &&
                Objects.equals(breed, farmer.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmerName, location, quantity, breed, id);
    }

    public Farmer(String farmerName, String location, int quantity, String breed){
        this.farmerName = farmerName;
        this.location = location;
        this.quantity = quantity;
        this.breed = breed;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

