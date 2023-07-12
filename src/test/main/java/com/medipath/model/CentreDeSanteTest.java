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

public class CentreDeSanteTest {
    
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
    }
    
    @AfterEach
    public void tearDown() {
        centreDeSante = null;
    }

    @Test
    public void testGetId() {
        assertEquals("123", centreDeSante.getId());
    }

    @Test
    public void testSetId() {
        centreDeSante.setId("456");
        assertEquals("456", centreDeSante.getId());
    }

    @Test
    public void testGetType() {
        assertEquals("TypeTest", centreDeSante.getType());
    }

    @Test
    public void testSetType() {
        centreDeSante.setType("TypeTest2");
        assertEquals("TypeTest2", centreDeSante.getType());
    }

    @Test
    public void testGetName() {
        assertEquals("CentreTest", centreDeSante.getName());
    }

    @Test
    public void testSetName() {
        centreDeSante.setName("CentreTest2");
        assertEquals("CentreTest2", centreDeSante.getName());
    }

    @Test
    public void testToString() {
        String expected = "CentreDeSante{ id=123, name=CentreTest, type=TypeTest }";
        assertEquals(expected, centreDeSante.toString());
    }
}