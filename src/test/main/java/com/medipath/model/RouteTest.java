/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.medipath.model;

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
public class RouteTest {

    private Route route;
    private CentreDeSante centreDeSante;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        centreDeSante = new CentreDeSante("123","TypeTest");
        centreDeSante.setName("CentreTest");
        route = new Route(centreDeSante,100,120,0.9);
    }

    @AfterEach
    public void tearDown() {
        route = null;
        centreDeSante = null;
    }

    @Test
    public void testGetDestination() {
        assertEquals(centreDeSante, route.getDestination());
    }

    @Test
    public void testSetDestination() {
        CentreDeSante newDestination = new CentreDeSante("456","TypeTest2");
        newDestination.setName("CentreTest2");
        route.setDestination(newDestination);
        assertEquals(newDestination, route.getDestination());
    }

    @Test
    public void testGetFiabilite() {
        assertEquals(0.9, route.getFiabilite(), 0.01);
    }

    @Test
    public void testSetFiabilite() {
        route.setFiabilite(0.8);
        assertEquals(0.8, route.getFiabilite(), 0.01);
    }

    @Test
    public void testGetDistance() {
        assertEquals(100, route.getDistance());
    }

    @Test
    public void testSetDistance() {
        route.setDistance(150);
        assertEquals(150, route.getDistance());
    }

    @Test
    public void testGetDureeMoyenne() {
        assertEquals(120, route.getDureeMoyenne());
    }

    @Test
    public void testSetDureeMoyenne() {
        route.setDureeMoyenne(200);
        assertEquals(200, route.getDureeMoyenne());
    }
}
