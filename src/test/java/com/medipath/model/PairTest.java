/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

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
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetKey() {
        pair = new Pair<>("TestKey", 1);
        System.out.println("getKey");
        String expectedKey = "TestKey";
        String actualKey = pair.getKey();
        assertEquals(expectedKey, actualKey);
    }

    @Test
    public void testGetValue() {
        pair = new Pair<>("TestKey", 1);
        System.out.println("getValue");
        Integer expectedValue = 1;
        Integer actualValue = pair.getValue();
        assertEquals(expectedValue, actualValue);
    }
}
