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
public class PairTest {
    
    private Pair<String, Integer> pair;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        pair = new Pair<>("Test", 123);
    }
    
    @AfterEach
    public void tearDown() {
        pair = null;
    }

    /**
     * Test of getKey method, of class Pair.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        String expResult = "Test";
        String result = pair.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class Pair.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int expResult = 123;
        int result = pair.getValue();
        assertEquals(expResult, result);
    }    
}