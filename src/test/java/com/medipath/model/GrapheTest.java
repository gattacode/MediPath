/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gatta
 */
public class GrapheTest {

    private CentreDeSante centreDeSante;
    private Route route;

    public GrapheTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddCentreDeSante() {
        centreDeSante = new CentreDeSante("123", "TypeTest");
        centreDeSante.setName("CentreTest");
        route = new Route(centreDeSante, 100, 120, 0.9);
        System.out.println("addCentreDeSante");
        Graphe instance = new Graphe();
        boolean result = instance.addCentreDeSante(centreDeSante.getId(), centreDeSante.getType());
        assertTrue(result);
    }

    @Test
    public void testAddRoute() {
        centreDeSante = new CentreDeSante("123", "TypeTest");
        centreDeSante.setName("CentreTest");
        System.out.println("addRoute");
        Graphe instance = new Graphe();
        instance.addCentreDeSante(centreDeSante.getId(), centreDeSante.getType());
        instance.addRoute(centreDeSante, route);
    }

    @Test
    public void testGetRoutesMap() {
        centreDeSante = new CentreDeSante("123", "TypeTest");
        centreDeSante.setName("CentreTest");
        System.out.println("getRoutesMap");
        Graphe instance = new Graphe();
        instance.addCentreDeSante(centreDeSante.getId(), centreDeSante.getType());
        instance.addRoute(centreDeSante, route);
        HashMap<CentreDeSante, ArrayList<Route>> result = instance.getRoutesMap();
        assertTrue(result.containsKey(centreDeSante) && result.get(centreDeSante).contains(route));
    }

    @Test
    public void testGetRoutes() {
        centreDeSante = new CentreDeSante("123", "TypeTest");
        centreDeSante.setName("CentreTest");
        System.out.println("getRoutes");
        Graphe instance = new Graphe();
        instance.addCentreDeSante(centreDeSante.getId(), centreDeSante.getType());
        instance.addRoute(centreDeSante, route);
        ArrayList<Route> result = instance.getRoutes(centreDeSante);
        assertTrue(result.contains(route));
    }

}
