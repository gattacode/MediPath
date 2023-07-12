/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.medipath.main;

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
public class MainApplicationTest {

    private MainApplication instance;

    public MainApplicationTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        // Code à exécuter une seule fois avant le début de tous les tests
    }

    @AfterAll
    public static void tearDownClass() {
        // Code à exécuter une seule fois après la fin de tous les tests
    }

    @BeforeEach
    public void setUp() {
        // Code à exécuter avant chaque test
        instance = new MainApplication();
    }

    @AfterEach
    public void tearDown() {
        // Code à exécuter après chaque test
        instance = null;
    }

    /**
     * Test of icon method, of class MainApplication.
     */
    @Test
    public void testIcon() {
        System.out.println("icon");
        assertDoesNotThrow(() -> instance.icon());
    }

    /**
     * Test of createGrapheInstance method, of class MainApplication.
     */
    @Test
    public void testCreateGrapheInstance() {
        System.out.println("createGrapheInstance");
        assertDoesNotThrow(() -> instance.createGrapheInstance());
        assertNotNull(instance.getGraphe()); // Cela suppose que vous avez une méthode getGraphe() dans la classe MainApplication
    }

    /**
     * Test of main method, of class MainApplication.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        assertDoesNotThrow(() -> MainApplication.main(args));
    }
}
