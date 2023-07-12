/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gatta
 */

public class RouteTest {
    private Route route;
    private CentreDeSante destination;
    
    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testGetDestination() {
        destination = new CentreDeSante("S1","M");
        route = new Route(destination, 100, 120, 0.8);
        CentreDeSante result = route.getDestination();
        assertEquals(destination, result, "Destination should match the one set in setup.");
    }

    @Test
    public void testSetDestination() {
        destination = new CentreDeSante("S1","M");
        route = new Route(destination, 100, 120, 0.8);
        CentreDeSante newDestination = new CentreDeSante("S1","M");
        route.setDestination(newDestination);
        CentreDeSante result = route.getDestination();
        assertEquals(newDestination, result, "Destination should be updated to new destination.");
    }

    @Test
    public void testGetFiabilite() {
        destination = new CentreDeSante("S1","M");
        route = new Route(destination, 100, 120, 0.8);
        double result = route.getFiabilite();
        assertEquals(0.8, result, "Fiabilité should match the one set in setup.");
    }

    @Test
    public void testSetFiabilite() {
        destination = new CentreDeSante("S1","M");
        route = new Route(destination, 100, 120, 0.8);
        route.setFiabilite(0.9);
        double result = route.getFiabilite();
        assertEquals(0.9, result, "Fiabilité should be updated to 0.9.");
    }

    @Test
    public void testGetDistance() {
        destination = new CentreDeSante("S1","M"); 
        route = new Route(destination, 100, 120, 0.8);
        int result = route.getDistance();
        assertEquals(100, result, "Distance should match the one set in setup.");
    }

    @Test
    public void testSetDistance() {
        destination = new CentreDeSante("S1","M"); 
        route = new Route(destination, 100, 120, 0.8);
        route.setDistance(150);
        int result = route.getDistance();
        assertEquals(150, result, "Distance should be updated to 150.");
    }

    @Test
    public void testGetDureeMoyenne() {
        destination = new CentreDeSante("S1","M");
        route = new Route(destination, 100, 120, 0.8);
        int result = route.getDureeMoyenne();
        assertEquals(120, result, "Duree moyenne should match the one set in setup.");
    }

    @Test
    public void testSetDureeMoyenne() {
        destination = new CentreDeSante("S1","M"); 
        route = new Route(destination, 100, 120, 0.8);
        route.setDureeMoyenne(130);
        int result = route.getDureeMoyenne();
        assertEquals(130, result, "Duree moyenne should be updated to 130.");
    }
}
