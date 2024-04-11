package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.prop.PropertyReader;
import org.example.services.ClientCrudService;
import org.example.services.PlanetCrudService;
import org.example.utils.HibernateUtils;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;

import java.util.List;

public class SpaceTravel {
    public static void main(String[] args) {
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();
        ClientCrudService ccs = new ClientCrudService();
        PlanetCrudService pcs = new PlanetCrudService();
        clientCruds(ccs);
        planetCruds(pcs);
        session.close();
    }

    public static void clientCruds(ClientCrudService ccs) {

        //create new client
        Client newClient = new Client();
        newClient.setName("Vitalii");
        ccs.createClient(newClient);
        System.out.printf("\nCreate new Client with id: %s and name: %s\n", newClient.getId(), newClient.getName());

        //get client by id
        Client client = ccs.getClientById(5L);
        System.out.println("Get client by id 5: " + client);

        //update client
        client.setName("Bohdan");
        ccs.updateClient(client);
        System.out.println("Update Client by id 5 to " + client.getName());


        //get all clients
        List<Client> clients = ccs.getAllClients();
        clients.forEach(allClient -> System.out.println("Client list ==> " + allClient));

        //delete client by id
        ccs.deleteClientById(11L);
        System.out.println("Delete client by Id 11");

        //delete all clients
//        ccs.deleteAllClients();
//        System.out.println("Delete all clients");
    }

    public static void planetCruds(PlanetCrudService pcs) {

        //create new planet
//        String planetId = "CRY";
//        String planetName = "Crypton";
        Planet planet = new Planet();
        planet.setId("CRY");
        planet.setName("Crypton");
        pcs.createPlanet(planet);
        System.out.printf("\nCreated planet: %s\n", planet);

        //get planet by id
        planet = pcs.getPlanetById("CRY");
        System.out.println("Result by planet id CRY: " + planet.getName());

        //update planet
        planet.setName("Neptun");
        pcs.updatePlanet(planet);
        System.out.println("Update planet by Id CRY to name Neptun");

        //get all planets
        List<Planet> planets = pcs.getAllPlanets();
        planets.forEach(allPlanets -> System.out.println("Planets list ==> " + allPlanets));

        //delete planet by id
        pcs.deletePlanetById("CRY");
        System.out.println("Delete planet by Id CRY");

        //delete planet
        pcs.deletePlanet(planet);
        System.out.println("Delete planet: " + planet);
    }

    }