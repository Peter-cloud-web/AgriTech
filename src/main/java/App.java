import java.util.Map;
import java.util.HashMap;

import dao.Sql2oFarmerDao;
import models.Farmer;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import java.util.List;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;




//App.java is responsible for the front-end user interface of the application.

//ROUTING
public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args){
        String connectionString = "jdbc:h2:~/agribusiness.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oFarmerDao farmerDao = new Sql2oFarmerDao(sql2o);
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

//        post("/hero/new", (request, response) -> { //URL to make new post on POST route
//            Map<String, Object> model = new HashMap<String, Object>();//We create our HashMap named model.// we use our Hero constructor to create a new Hero with the user's provided content.
//            String name = request.queryParams("name");
//            int age = Integer.parseInt(request.queryParams("age"));
//            String power = request.queryParams("power");
//            String weakness = request.queryParams("weakness");
//            Hero newHero = new Hero(name, age, power, weakness);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/squad/new", (request, response) -> { //URL to make new post on POST route
//            Map<String, Object> model = new HashMap<String, Object>();//We create our HashMap named model.// we use our Hero constructor to create a new Hero with the user's provided content.
//            String name = request.queryParams("name");
//            String cause = request.queryParams("cause");
//            Squad newSquad = new Squad(name, cause);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Hero> heroes = Hero.getAllHeroes();
//            int totalHeroes = Hero.getAllHeroes().size();
//            int totalSquads = Squad.getAllSquad().size();
//            model.put("totalHeroes", totalHeroes);
//            model.put("totalSquads", totalSquads);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/layout", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "layout.hbs");
        }, new HandlebarsTemplateEngine());
        get("/fruits", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "fruits.hbs");
        }, new HandlebarsTemplateEngine());
        get("/vegetables", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "vegetables.hbs");
        }, new HandlebarsTemplateEngine());
        get("/animalsProducts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());
        get("/cereals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "cereals.hbs");
        }, new HandlebarsTemplateEngine());
        get("/beverage", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "beverageAndSpice.hbs");
        }, new HandlebarsTemplateEngine());
        get("/blog", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "blog.hbs");
        }, new HandlebarsTemplateEngine());
        get("/resources", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "resources.hbs");
        }, new HandlebarsTemplateEngine());
        get("/terms", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "cart.hbs");
        }, new HandlebarsTemplateEngine());

        post("/welcome",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            String username = request.queryParams("username");
            request.session().attribute("userName", username);
            model.put("username", username);
            return  new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        get("/farm", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "farmer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/farmers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String farmerName = req.queryParams("farmerName");
            String location = req.queryParams("location");
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String breed = req.queryParams("breed");
            Farmer newFarmer = new Farmer(farmerName, location, quantity, breed);
            farmerDao.addFarmer(newFarmer);
            model.put("username", req.session().attribute("username"));
            model.put("farmer", newFarmer);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



        get("/farmers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Farmer> farmer = farmerDao.getAll();
            model.put("farmers", farmer);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request,response)-> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "login.hbs");
        }, new HandlebarsTemplateEngine());
//
//        get("/heroes", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("allHeroes", Hero.getAllHeroes());
//            model.put("allSquads", Squad.getAllSquad());
//            return new ModelAndView(model, "heroes-squads.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/squad/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
////                int idOfSquadToFind = Integer.parseInt(req.params("id"));
////                Squad foundSquad = Squad.findById(idOfSquadToFind);
////                model.put("squad", foundSquad);
//            return new ModelAndView(model, "squad-details.hbs"); //individual squad page.
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/hero/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfHeroToFind = Integer.parseInt(req.params("id"));
//            Hero foundHero = Hero.findById(idOfHeroToFind);
//            model.put("hero", foundHero);
//            return new ModelAndView(model, "hero-detail.hbs"); //individual hero page.
//        }, new HandlebarsTemplateEngine());

    }
}

