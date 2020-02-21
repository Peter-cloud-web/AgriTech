package dao;

import models.Farmer;
import java.util.List;

public interface FarmerDao {

    // LIST
    List<Farmer> getAll();

    // CREATE
    void addFarmer(Farmer farmer);

    // READ
    Farmer findById(int id);

    // UPDATE
    void update(int id, String farmerName, String location, int quantity, String breed);

    // DELETE
    void deleteById(int id);
    void clearAllFarmers();
}