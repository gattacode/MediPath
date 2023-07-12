/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

/**
 *
 * 
 * Classe représentant une route entre deux centres de santé.
 * @author gatta
 */
public class Route {

    private CentreDeSante destination;
    
    private double fiabilite;
    
    private int distance;
    
    private int dureeMoyenne;

    /**
     * Constructeur de la classe Route.
     *
     * @param destination La destination de la route.
     * @param distance La distance de la route en kilomètres.
     * @param dureeMoyenne La durée moyenne de trajet sur la route en minutes.
     * @param fiabilite La fiabilité de la route (valeur entre 0 et 1).
     */
    public Route(CentreDeSante destination, int distance, int dureeMoyenne, double fiabilite) {
        this.destination = destination;
        this.fiabilite = fiabilite;
        this.distance = distance;
        this.dureeMoyenne = dureeMoyenne;
    }

    /**
     * Retourne la destination de la route.
     *
     * @return La destination de la route.
     */
    public CentreDeSante getDestination() {
        return destination;
    }

    /**
     * Définit la destination de la route.
     *
     * @param destination La nouvelle destination de la route.
     */
    public void setDestination(CentreDeSante destination) {
        this.destination = destination;
    }

    /**
     * Retourne la fiabilité de la route.
     *
     * @return La fiabilité de la route (valeur entre 0 et 1).
     */
    public double getFiabilite() {
        return fiabilite;
    }

    /**
     * Définit la fiabilité de la route.
     *
     * @param fiabilite La nouvelle fiabilité de la route (valeur entre 0 et 1).
     */
    public void setFiabilite(double fiabilite) {
        this.fiabilite = fiabilite;
    }

    /**
     * Retourne la distance de la route.
     *
     * @return La distance de la route en kilomètres.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Définit la distance de la route.
     *
     * @param distance La nouvelle distance de la route en kilomètres.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Retourne la durée moyenne de trajet sur la route.
     *
     * @return La durée moyenne de trajet sur la route en minutes.
     */
    public int getDureeMoyenne() {
        return dureeMoyenne;
    }

    /**
     * Définit la durée moyenne de trajet sur la route.
     *
     * @param dureeMoyenne La nouvelle durée moyenne de trajet sur la route en
     * minutes.
     */
    public void setDureeMoyenne(int dureeMoyenne) {
        this.dureeMoyenne = dureeMoyenne;
    }
}
