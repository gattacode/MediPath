/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

/**
 * Pair est une classe simple qui stocke deux objets liés.
 * Cette classe peut être utilisée lorsque vous souhaitez renvoyer deux valeurs d'une méthode ou stocker deux objets liés dans un seul objet.
 *
 * @param <K> Le type du premier objet dans la Pair (appelé clé).
 * @param <V> Le type du deuxième objet dans la Pair (appelé valeur).
 * @author gatta
 */
public class Pair<K, V> {

    /**
     * Le premier objet (appelé clé) de cette Pair.
     */
    private final K key;

    /**
     * Le deuxième objet (appelé valeur) de cette Pair.
     */
    private final V value;

    /**
     * Construit une nouvelle Pair avec les valeurs spécifiées.
     *
     * @param key le premier objet de la Pair
     * @param value le deuxième objet de la Pair
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Renvoie la clé contenue dans cette Pair.
     *
     * @return la clé contenue dans cette Pair
     */
    public K getKey() {
        return key;
    }

    /**
     * Renvoie la valeur contenue dans cette Pair.
     *
     * @return la valeur contenue dans cette Pair
     */
    public V getValue() {
        return value;
    }
}
