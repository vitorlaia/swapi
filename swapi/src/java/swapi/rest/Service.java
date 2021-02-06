/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swapi.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import swapi.obj.People;
import swapi.obj.Planet;
import swapi.obj.Starships;
import swapi.obj.Travel;

/**
 *
 * @author Vitor
 */
@Path("/")
public class Service {

    @GET
    @Path("/people")
    @Produces({ "application/json" })
    public Response peopleList(){
    
        String url = "https://swapi.dev/api/people/";

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");
        ArrayList<People> lista = new ArrayList<People>();
        
        int t = jsonArray.length();
        
        for(int i = 0; i < t; ++i){
            JSONObject obj = jsonArray.getJSONObject(i);    
            
            People people = new People();
            
            people.setName(obj.getString("name"));
            people.setHeight(obj.getInt("height"));
            people.setMass(obj.getInt("mass"));
            people.setHairColor(obj.getString("hair_color"));
            people.setSkinColor(obj.getString("skin_color"));
            people.setEyeColor(obj.getString("eye_color"));            
            people.setBirthYear(obj.getString("birth_year"));
            people.setGender(obj.getString("gender"));
            people.setHomeworld(obj.getString("homeworld"));
            
            ArrayList<String> films = new ArrayList<String>();
            for(Object o : obj.getJSONArray("films").toList()){
                films.add(getInfo(o.toString(),"title"));
            }
            people.setFilms(films);
            
            ArrayList<String> species = new ArrayList<String>();
            for(Object o : obj.getJSONArray("species").toList()){
                species.add(getInfo(o.toString(),"name"));
            }
            people.setSpecies(species);
            
            ArrayList<String> vehicles = new ArrayList<String>();
            for(Object o : obj.getJSONArray("vehicles").toList()){
                vehicles.add(getInfo(o.toString(),"name"));
            }
            people.setVehicles(vehicles);
            
            ArrayList<String> starships = new ArrayList<String>();
            for(Object o : obj.getJSONArray("starships").toList()){
                starships.add(getInfo(o.toString(),"name"));
            }           
            people.setStarships(starships);
            
            people.setCreated(obj.getString("created"));
            people.setEdited(obj.getString("edited"));
            people.setUrl(obj.getString("url"));
            
            lista.add(people);
        }
        
                
        return Response.status(Response.Status.OK).entity(lista).build();
        
    }
    
    @GET
    @Path("/people/{name}")
    @Produces({ "application/json" })
    public Response getPeople(@PathParam("name") String name ){
    
        String url = "https://swapi.dev/api/people/?search="+name;

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");

        People people = new People();
        if(jsonArray.length() == 0){
                  return Response.status(Response.Status.OK).entity(people).build();
              }
        JSONObject obj = jsonArray.getJSONObject(0);
        
        people.setName(obj.getString("name"));
        people.setHeight(obj.getInt("height"));
        people.setMass(obj.getInt("mass"));
        people.setHairColor(obj.getString("hair_color"));
        people.setSkinColor(obj.getString("skin_color"));
        people.setEyeColor(obj.getString("eye_color"));            
        people.setBirthYear(obj.getString("birth_year"));
        people.setGender(obj.getString("gender"));
        people.setHomeworld(obj.getString("homeworld"));

        ArrayList<String> films = new ArrayList<String>();
        for(Object o : obj.getJSONArray("films").toList()){
            films.add(getInfo(o.toString(),"title"));
        }
        people.setFilms(films);

        ArrayList<String> species = new ArrayList<String>();
        for(Object o : obj.getJSONArray("species").toList()){
            species.add(getInfo(o.toString(),"name"));
        }
        people.setSpecies(species);

        ArrayList<String> vehicles = new ArrayList<String>();
        for(Object o : obj.getJSONArray("vehicles").toList()){
            vehicles.add(getInfo(o.toString(),"name"));
        }
        people.setVehicles(vehicles);

        ArrayList<String> starships = new ArrayList<String>();
        for(Object o : obj.getJSONArray("starships").toList()){
            starships.add(getInfo(o.toString(),"name"));
        }           
        people.setStarships(starships);

        people.setCreated(obj.getString("created"));
        people.setEdited(obj.getString("edited"));
        people.setUrl(obj.getString("url"));
                            
        return Response.status(Response.Status.OK).entity(people).build();
        
    }
    
    @GET
    @Path("/planet")
    @Produces({ "application/json" })
    public Response planetList(){
    
        String url = "https://swapi.dev/api/planets/";

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");
        ArrayList<Planet> lista = new ArrayList<Planet>();
        
        int t = jsonArray.length();
        
        for(int i = 0; i < t; ++i){
            JSONObject obj = jsonArray.getJSONObject(i);    
            
            Planet planet = new Planet();
            
            planet.setName(obj.getString("name"));
            planet.setRotationPeriod(obj.getInt("rotation_period"));
            planet.setOrbitalPeriod(obj.getInt("orbital_period"));
            planet.setDiameter(obj.getInt("diameter"));
            planet.setClimate(obj.getString("climate"));
            planet.setGravity(obj.getString("gravity"));            
            planet.setTerrain(obj.getString("terrain"));
            planet.setSurfaceWater(obj.getString("surface_water"));
            
            try{
            planet.setPopulation(obj.getLong("population"));
            }catch(Exception e){
                
            }
            
            ArrayList<String> films = new ArrayList<String>();
            for(Object o : obj.getJSONArray("films").toList()){
                films.add(getInfo(o.toString(),"title"));
            }
            planet.setFilms(films);
            
            ArrayList<String> residents = new ArrayList<String>();
            for(Object o : obj.getJSONArray("residents").toList()){
                residents.add(getInfo(o.toString(),"name"));
            }
            planet.setResidents(residents);
 
            planet.setCreated(obj.getString("created"));
            planet.setEdited(obj.getString("edited"));
            planet.setUrl(obj.getString("url"));
            
            lista.add(planet);
        }
        
                
        return Response.status(Response.Status.OK).entity(lista).build();
        
    }
    
    @GET
    @Path("/planet/{name}")
    @Produces({ "application/json" })
    public Response getPlanet(@PathParam("name") String name){
    
        String url = "https://swapi.dev/api/planets/?search="+name;

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");

        JSONObject obj = jsonArray.getJSONObject(0);    

        Planet planet = new Planet();

        planet.setName(obj.getString("name"));
        planet.setRotationPeriod(obj.getInt("rotation_period"));
        planet.setOrbitalPeriod(obj.getInt("orbital_period"));
        planet.setDiameter(obj.getInt("diameter"));
        planet.setClimate(obj.getString("climate"));
        planet.setGravity(obj.getString("gravity"));            
        planet.setTerrain(obj.getString("terrain"));
        planet.setSurfaceWater(obj.getString("surface_water"));

        try{
        planet.setPopulation(obj.getLong("population"));
        }catch(Exception e){

        }

        ArrayList<String> films = new ArrayList<String>();
        for(Object o : obj.getJSONArray("films").toList()){
            films.add(getInfo(o.toString(),"title"));
        }
        planet.setFilms(films);

        ArrayList<String> residents = new ArrayList<String>();
        for(Object o : obj.getJSONArray("residents").toList()){
            residents.add(getInfo(o.toString(),"name"));
        }
        planet.setResidents(residents);

        planet.setCreated(obj.getString("created"));
        planet.setEdited(obj.getString("edited"));
        planet.setUrl(obj.getString("url"));
            

        return Response.status(Response.Status.OK).entity(planet).build();
        
    }
    
    @GET
    @Path("/starships")
    @Produces({ "application/json" })
    public Response starshipList(){
    
        String url = "https://swapi.dev/api/starships/";

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");
        ArrayList<Starships> lista = new ArrayList<Starships>();
        
        int t = jsonArray.length();
        
        for(int i = 0; i < t; ++i){
            JSONObject obj = jsonArray.getJSONObject(i);    
            
            Starships starships = new Starships();
            
            starships.setName(obj.getString("name"));
            starships.setModel(obj.getString("model"));
            starships.setManufacturer(obj.getString("manufacturer"));
            
            try{
            starships.setCostInCredits(obj.getLong("cost_in_credits"));
            }catch(Exception e){
                
            }
            
            String aux = obj.getString("length");
            aux=aux.replace(",",""); 
            
            starships.setLength(Float.parseFloat(aux));

            try{
            starships.setMaxAtmospheringSpeed(obj.getInt("max_atmosphering_speed")); 
            }catch(Exception e){
                
            }
            
            starships.setCrew(obj.getString("crew"));
            
            try{
            starships.setPassengers(obj.getInt("passengers"));
            }catch(Exception e){
                
            }
            
            starships.setCargoCapacity(obj.getLong("cargo_capacity"));
            starships.setConsumables(obj.getString("consumables"));
            starships.setHyperdriveRating(obj.getFloat("hyperdrive_rating"));
            starships.setMglt(obj.getInt("MGLT"));
            starships.setStarshipClass(obj.getString("starship_class"));
            
            ArrayList<String> films = new ArrayList<String>();
            for(Object o : obj.getJSONArray("films").toList()){
                films.add(getInfo(o.toString(),"title"));
            }
            starships.setFilms(films);
            
            starships.setCreated(obj.getString("created"));
            starships.setEdited(obj.getString("edited"));
            starships.setUrl(obj.getString("url"));
            
            lista.add(starships);
        }
        
                
        return Response.status(Response.Status.OK).entity(lista).build();
        
    }    
       
    
    @GET
    @Path("/starships/{name}")
    @Produces({ "application/json" })
    public Response getStarship(@PathParam("name") String name){
    
        String url = "https://swapi.dev/api/starships/?search="+name;

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");

        JSONObject obj = jsonArray.getJSONObject(0);    

        Starships starships = new Starships();

        starships.setName(obj.getString("name"));
        starships.setModel(obj.getString("model"));
        starships.setManufacturer(obj.getString("manufacturer"));

        try{
        starships.setCostInCredits(obj.getLong("cost_in_credits"));
        }catch(Exception e){

        }

        String aux = obj.getString("length");
        aux=aux.replace(",",""); 

        starships.setLength(Float.parseFloat(aux));

        try{
        starships.setMaxAtmospheringSpeed(obj.getInt("max_atmosphering_speed")); 
        }catch(Exception e){

        }

        starships.setCrew(obj.getString("crew"));

        try{
        starships.setPassengers(obj.getInt("passengers"));
        }catch(Exception e){

        }

        starships.setCargoCapacity(obj.getLong("cargo_capacity"));
        starships.setConsumables(obj.getString("consumables"));
        starships.setHyperdriveRating(obj.getFloat("hyperdrive_rating"));
        starships.setMglt(obj.getInt("MGLT"));
        starships.setStarshipClass(obj.getString("starship_class"));

        ArrayList<String> films = new ArrayList<String>();
        for(Object o : obj.getJSONArray("films").toList()){
            films.add(getInfo(o.toString(),"title"));
        }
        starships.setFilms(films);

        starships.setCreated(obj.getString("created"));
        starships.setEdited(obj.getString("edited"));
        starships.setUrl(obj.getString("url"));
   
        return Response.status(Response.Status.OK).entity(starships).build();
        
    }    
       
    
    public String getInfo(String url, String key){
        if(url.trim().length() == 0){
            return "";
        }
        url=url.replace("http","https");
        
        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        String titulo = json.getString(key);
        return titulo;
    }

    @GET
    @Path("/starships/distancia/{distancia}")
    @Produces({"application/json"})
    public Response parada(@PathParam("distancia") int distancia){

        String url = "https://swapi.dev/api/starships/";

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");
        ArrayList<Travel> travelList = new ArrayList<Travel>();
        
        int t = jsonArray.length();
        int parada=0;
        int aux=0;
        String paradas;
        
        for(int i=0;i<t;i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            Travel travel = new Travel();
                    
            try{
                aux=obj.getInt("MGLT");
            }catch(Exception e){
                
            }
            
            if(aux == 0){
                paradas="Sem dados para calculo";
            }
            else{
                parada=distancia/aux;
                paradas=Integer.toString(parada);
            }
          travel.setName(obj.getString("name"));
          travel.setQtyParada(paradas);

          travelList.add(travel);
        }
        return Response.status(Response.Status.OK).entity(travelList).build();

}

    @GET
    @Path("/starships/best/{passageiros}/{distancia}")
    @Produces({"application/json"})
    public Response melhorNave(@PathParam("passageiros") int passageiros,@PathParam("distancia") float distancia){
        String url = "https://swapi.dev/api/starships/";

        Client rClient = Client.create();

        WebResource wResource = rClient.resource(url);
        
        ClientResponse response = wResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
        }

        String resp = response.getEntity(String.class);
        
        JSONObject json = new JSONObject(resp);
        JSONArray jsonArray = json.getJSONArray("results");
        ArrayList<Travel> travelList = new ArrayList<Travel>(); 
        Travel travel = new Travel();
        
        int t = jsonArray.length();
        int pass =0;
        float paradas=0;
        float mglt=0;
        float paradasAnt=0;
        String nomeNave="";
        
        for(int i=0;i<t;i++){
           JSONObject obj = jsonArray.getJSONObject(i);
           
           try{
           pass=obj.getInt("passengers");
           }catch(Exception e){
               
           }
           
           if(pass>=passageiros){
               mglt=obj.getFloat("MGLT");
               paradas=distancia/mglt;
               
               if(paradasAnt==0){
                   paradasAnt=paradas;
               }
               
               if(paradas<=paradasAnt){
                   paradasAnt=paradas;
                   nomeNave=obj.getString("name");
               }  
           }  
        }
        travel.setName(nomeNave);
        travelList.add(travel);
        return Response.status(Response.Status.OK).entity(travelList).build();
    }
}


    