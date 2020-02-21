package dao;import models.Farmer;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;public class Sql2oFarmerDaoTest {
    private Sql2oFarmerDao farmerDao;
    private Connection conn;    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        farmerDao = new Sql2oFarmerDao(sql2o);
        conn = sql2o.open();
    }    @After
    public void tearDown() throws Exception {
        conn.close();
    }    Farmer testFarmer(){
        return new Farmer("farmer", "nairobi",3, "freshian");
    }    @Test
    public void addingFarmerSetsId() throws Exception {
        Farmer farmer = testFarmer();
        int originalFarmerId = farmer.getId();
        farmerDao.addFarmer(farmer);
        assertNotEquals(originalFarmerId, farmer.getId());
    }    @Test
    public void existingFarmersCanBeFoundById() throws Exception {
        Farmer farmer = testFarmer();
        farmerDao.addFarmer(farmer);
        Farmer foundFarmer = farmerDao.findById(farmer.getId());
        assertEquals(farmer, foundFarmer);
    }    @Test
    public void ifNoFarmerAddedReturnsEmpty() throws Exception{
        assertEquals(0, farmerDao.getAll().size());
    }    @Test
    public void updateFarmerInformationUpdatesInformationCorrectly() throws Exception{
        Farmer farmer = testFarmer();
        farmerDao.addFarmer(farmer);
        farmerDao.update(farmer.getId(), "tarzan", "nakuru",4, "bulldog");
        Farmer newFarmer = farmerDao.findById(farmer.getId());
        assertNotEquals(farmer.getFarmerName(), newFarmer.getFarmerName());
        assertNotEquals(farmer.getLocation(), newFarmer.getLocation());
        assertNotEquals(farmer.getQuantity(), newFarmer.getQuantity());
        assertNotEquals(farmer.getBreed(), newFarmer.getBreed());
    }    @Test
    public void deleteByIdDeletesSingleFarmer() throws Exception {
        Farmer farmer = testFarmer();
        Farmer anotherFarmer = new Farmer("Moli", "riverside", 4, "bulldog");
        farmerDao.addFarmer(farmer);
        farmerDao.addFarmer(anotherFarmer);
        farmerDao.deleteById(farmer.getId());
        assertEquals(1, farmerDao.getAll().size());
    }    @Test
    public void deleteAllClearsAllFarmers() throws Exception{
        Farmer farmer = testFarmer();
        Farmer anotherFarmer = new Farmer("Moli", "riverside", 4, "bulldog");
        farmerDao.addFarmer(farmer);
        farmerDao.addFarmer(anotherFarmer);
        farmerDao.clearAllFarmers();
        assertEquals(0, farmerDao.getAll().size());
    }}