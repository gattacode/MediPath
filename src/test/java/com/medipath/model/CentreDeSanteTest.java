/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CentreDeSante class.
 */
public class CentreDeSanteTest {

    /**
     * Test for the getId() method.
     * It checks if the returned ID matches the expected ID.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        String expResult = "1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test for the setId() method.
     * It sets the ID of the instance and verifies if it matches the provided ID.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "2";
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test for the getType() method.
     * It checks if the returned type matches the expected type.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        String expResult = "Hôpital";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test for the setType() method.
     * It sets the type of the instance and verifies if it matches the provided type.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "Clinique";
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        instance.setType(type);
        assertEquals(type, instance.getType());
    }

    /**
     * Test for the getName() method.
     * It checks if the returned name matches the expected name.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        instance.setName("Centre médical ABC");
        String expResult = "Centre médical ABC";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test for the setName() method.
     * It sets the name of the instance and verifies if it matches the provided name.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Clinique XYZ";
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test for the toString() method.
     * It checks if the returned string representation matches the expected value.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CentreDeSante instance = new CentreDeSante("1", "Hôpital");
        String expResult = "1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}