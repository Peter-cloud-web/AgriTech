package models;import org.junit.Test;import static org.junit.Assert.*;public class FarmerTest {
    Farmer testFarmer(){
        return new Farmer("farmer", "nairobi",3, "freshian");
    }    @Test
    public void farmerInstantiatesCorrectly() throws Exception{
        Farmer farmer1 = testFarmer();
        assertTrue(farmer1 instanceof Farmer);
    }    @Test
    public void farmerGetterMethodsWorkCorrectly() throws Exception{
        Farmer farmer1 = testFarmer();
        assertEquals("farmer", farmer1.getFarmerName());
        assertEquals("nairobi", farmer1.getLocation());
        assertEquals(3, farmer1.getQuantity());
        assertEquals("freshian", farmer1.getBreed());
    }    @Test
    public void farmerSettersWorkCorrectlySettingNewValues() throws Exception{
        Farmer farmer1 = testFarmer();
    }
}