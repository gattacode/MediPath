/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

/**
 *
 *
 * Classe représentant un Centre de Santé dans la ville.
 *
 * @author gatta
 */
public class CentreDeSante {

    private String id;
    
    private String type;
    
    private String nom;

    /**
     * Constructeur de la classe CentreDeSante.
     *
     * @param id L'identifiant du centre de santé.
     * @param type Le type de centre de santé.
     */
    public CentreDeSante(String id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Retourne l'identifiant du centre de santé.
     *
     * @return L'identifiant du centre de santé.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant du centre de santé.
     *
     * @param id Le nouvel identifiant du centre de santé.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne le type de centre de santé.
     *
     * @return Le type de centre de santé.
     */
    public String getType() {
        return type;
    }

    /**
     * Définit le type de centre de santé.
     *
     * @param type Le nouveau type de centre de santé.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Retourne le type de centre de santé.
     *
     * @return Le type de centre de santé.
     */
    public String getName() {
        return nom;
    }

    /**
     * Définit le type de centre de santé.
     *
     * @param nom Le nouveau type de centre de santé.
     * 
     */
    public void setName(String nom) {
        this.nom = nom;
    }
    
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
