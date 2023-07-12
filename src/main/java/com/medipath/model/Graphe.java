/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medipath.model;

/**
 *
 * @author gatta
 */
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Classe représentant un graphe composé de centres de santé reliés par des
 * routes.
 *
 * @author gatta
 */
public class Graphe {

    private final ArrayList<CentreDeSante> centreDeSanteList;

    private final HashMap<CentreDeSante, ArrayList<Route>> routesMap;

    /**
     * Constructeur de la classe Graphe. Initialise une liste de centres de
     * santé et une map de routes.
     */
    public Graphe() {
        centreDeSanteList = new ArrayList<>();
        routesMap = new HashMap<>();
    }

    /**
     * Ajoute un nouveau centre de santé au graphe.
     *
     * @param id Identifiant du centre de santé.
     * @param type Type du centre de santé.
     * @return true si l'ajout est réussi, false sinon.
     */
    public boolean addCentreDeSante(String id, String type) {
        CentreDeSante centre = new CentreDeSante(id, type);
        centreDeSanteList.add(centre);
        routesMap.put(centre, new ArrayList<>());
        return true;
    }

    /**
     * Ajoute une nouvelle route entre deux centres de santé existants.
     *
     * @param centreDeDepart Centre de santé de départ de la route.
     * @param centreDArrivee Centre de santé d'arrivée de la route.
     * @param distance Distance de la route.
     * @param dureeMoyenne Durée moyenne de la route.
     * @param fiabilite Fiabilité de la route.
     */
    public void addRoute(CentreDeSante centreDeDepart, CentreDeSante centreDArrivee, int distance, int dureeMoyenne, double fiabilite) {
        if (!centreDeSanteList.contains(centreDeDepart)) {
            ArrayList<Route> listeRoutes = new ArrayList<>();
            listeRoutes.add(new Route(centreDArrivee, distance, dureeMoyenne, fiabilite));
            routesMap.put(centreDeDepart, listeRoutes);
            centreDeSanteList.add(centreDeDepart);
        }
        routesMap.get(centreDeDepart).add(new Route(centreDArrivee, distance, dureeMoyenne, fiabilite));
    }

    /**
     * Retourne la liste des centres de santé du graphe.
     *
     * @return Liste des centres de santé.
     */
    public ArrayList<CentreDeSante> getCentreDeSanteList() {
        return centreDeSanteList;
    }

    /**
     * Retourne la map des routes du graphe.
     *
     * @return Map des routes.
     */
    public HashMap<CentreDeSante, ArrayList<Route>> getRoutesMap() {
        return routesMap;
    }

    /**
     * Retourne les routes partant d'un centre de santé donné.
     *
     * @param centre Centre de santé de départ.
     * @return Liste des routes partant du centre de santé.
     */
    public ArrayList<Route> getRoutes(CentreDeSante centre) {
        return routesMap.get(centre);
    }

    /**
     * Remplit le graphe à partir de fichiers contenant les données d'adjacence
     * et de successeurs.
     *
     * @param fichierListeDAjdacencePath Chemin du fichier de liste d'adjacence.
     * @param fichierListeSucceseursPath Chemin du fichier de liste de
     * successeurs.
     * @return true si le remplissage est réussi, false sinon.
     */
    public boolean remplirGrapheViaFichier(String fichierListeDAjdacencePath, String fichierListeSucceseursPath) {
        String[] fichiers = verifierFichiersEtAssignerCheminParDefaut(fichierListeDAjdacencePath, fichierListeSucceseursPath);
        String adjacenceFichier = fichiers[0];
        String successeurFichier = fichiers[1];
        boolean fichierCorrect = Boolean.parseBoolean(fichiers[2]);

        try {
            List<String> ligneAdjaList = lireFichier(adjacenceFichier);
            List<String> ligneSuccList = lireFichier(successeurFichier);

            ajouterCentresDeSanteViaListe(ligneAdjaList);
            ajouterRoutesViaListe(ligneAdjaList, ligneSuccList);

        } catch (NumberFormatException e) {
            // handle exception
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fichierCorrect;
    }

    /**
     * Recherche le chemin le plus court entre deux centres de santé en
     * utilisant la distance comme critère.
     *
     * @param source Centre de santé de départ.
     * @param destination Centre de santé d'arrivée.
     * @return Paire contenant le chemin le plus court (liste de centres de
     * santé) et la distance totale du chemin.
     */
    public Pair<ArrayList<CentreDeSante>, Integer> shortestPath(CentreDeSante source, CentreDeSante destination) {
        // Map pour stocker les prédécesseurs de chaque centre de santé
        Map<CentreDeSante, CentreDeSante> previousCentre = new HashMap<>();
        // Map pour stocker les distances depuis la source jusqu'à chaque centre de santé
        Map<CentreDeSante, Integer> distances = new HashMap<>();
        // File de priorité pour ordonner les centres de santé en fonction de leurs distances
        PriorityQueue<CentreDeSante> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (CentreDeSante centre : centreDeSanteList) {
            // Initialisation des distances à l'infini sauf pour la source qui est à 0
            if (centre.equals(source)) {
                distances.put(centre, 0);
            } else {
                distances.put(centre, Integer.MAX_VALUE);
            }
            queue.add(centre);
        }

        while (!queue.isEmpty()) {
            CentreDeSante current = queue.poll();

            if (current.equals(destination)) {
                break;
            }

            for (Route route : routesMap.get(current)) {
                CentreDeSante neighbour = route.getDestination();
                int distanceThroughCurrent = distances.get(current) + route.getDistance();

                // Mise à jour de la distance si un chemin plus court est trouvé
                if (distanceThroughCurrent < distances.get(neighbour)) {
                    distances.put(neighbour, distanceThroughCurrent);
                    previousCentre.put(neighbour, current);
                    // Mise à jour de la position du voisin dans la file de priorité
                    queue.remove(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        if (previousCentre.get(destination) == null) {
            return null;
        }

        // Reconstruction du chemin à partir des prédécesseurs
        ArrayList<CentreDeSante> path = new ArrayList<>();
        CentreDeSante centre = destination;

        while (centre != null) {
            path.add(centre);
            centre = previousCentre.get(centre);
        }

        Collections.reverse(path);

        return new Pair<>(path, distances.get(destination));
    }

    /**
     * Recherche le chemin le plus court entre deux centres de santé en
     * utilisant la durée moyenne comme critère.
     *
     * @param source Centre de santé de départ.
     * @param destination Centre de santé d'arrivée.
     * @return Paire contenant le chemin le plus court (liste de centres de
     * santé) et la durée totale du chemin.
     */
    public Pair<ArrayList<CentreDeSante>, Integer> shortestPathDureeMoyenne(CentreDeSante source, CentreDeSante destination) {
        // Map pour stocker les prédécesseurs de chaque centre de santé
        Map<CentreDeSante, CentreDeSante> previousCentre = new HashMap<>();
        // Map pour stocker les durées moyennes depuis la source jusqu'à chaque centre de santé
        Map<CentreDeSante, Integer> durations = new HashMap<>();
        // File de priorité pour ordonner les centres de santé en fonction de leurs durées moyennes
        PriorityQueue<CentreDeSante> queue = new PriorityQueue<>(Comparator.comparingInt(durations::get));

        for (CentreDeSante centre : centreDeSanteList) {
            // Initialisation des durées à l'infini sauf pour la source qui est à 0
            if (centre.equals(source)) {
                durations.put(centre, 0);
            } else {
                durations.put(centre, Integer.MAX_VALUE);
            }
            queue.add(centre);
        }

        while (!queue.isEmpty()) {
            CentreDeSante current = queue.poll();

            if (current.equals(destination)) {
                break;
            }

            for (Route route : routesMap.get(current)) {
                CentreDeSante neighbour = route.getDestination();
                int durationThroughCurrent = durations.get(current) + route.getDureeMoyenne();

                // Mise à jour de la durée si un chemin plus court est trouvé
                if (durationThroughCurrent < durations.get(neighbour)) {
                    durations.put(neighbour, durationThroughCurrent);
                    previousCentre.put(neighbour, current);
                    // Mise à jour de la position du voisin dans la file de priorité
                    queue.remove(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        if (previousCentre.get(destination) == null) {
            return null;
        }

        // Reconstruction du chemin à partir des prédécesseurs
        ArrayList<CentreDeSante> path = new ArrayList<>();
        CentreDeSante centre = destination;

        while (centre != null) {
            path.add(centre);
            centre = previousCentre.get(centre);
        }

        Collections.reverse(path);

        return new Pair<>(path, durations.get(destination));
    }

    /**
     * Recherche le chemin le plus fiable entre deux centres de santé.
     *
     * @param source Centre de santé de départ.
     * @param destination Centre de santé d'arrivée.
     * @return Paire contenant le chemin le plus fiable (liste de centres de
     * santé) et la fiabilité totale du chemin.
     */
    public Pair<ArrayList<CentreDeSante>, Double> mostReliablePath(CentreDeSante source, CentreDeSante destination) {
        // Map pour stocker les prédécesseurs de chaque centre de santé
        Map<CentreDeSante, CentreDeSante> previousCentre = new HashMap<>();
        // Map pour stocker les fiabilités cumulées depuis la source jusqu'à chaque centre de santé
        Map<CentreDeSante, Double> reliability = new HashMap<>();
        // File de priorité pour ordonner les centres de santé en fonction de leurs fiabilités cumulées (ordre décroissant)
        PriorityQueue<CentreDeSante> queue = new PriorityQueue<>(Comparator.comparingDouble(reliability::get).reversed());

        for (CentreDeSante centre : centreDeSanteList) {
            // Initialisation des fiabilités à 1.0 pour la source, 0.0 pour les autres centres
            if (centre.equals(source)) {
                reliability.put(centre, 1.0);
            } else {
                reliability.put(centre, 0.0);
            }
            queue.add(centre);
        }

        while (!queue.isEmpty()) {
            CentreDeSante current = queue.poll();

            if (current.equals(destination)) {
                break;
            }

            for (Route route : routesMap.get(current)) {
                CentreDeSante neighbour = route.getDestination();
                double reliabilityThroughCurrent = reliability.get(current) * (route.getFiabilite() / 100);

                // Mise à jour de la fiabilité cumulée si une fiabilité plus élevée est trouvée
                if (reliabilityThroughCurrent > reliability.get(neighbour)) {
                    reliability.put(neighbour, reliabilityThroughCurrent);
                    previousCentre.put(neighbour, current);
                    // Mise à jour de la position du voisin dans la file de priorité
                    queue.remove(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        if (previousCentre.get(destination) == null) {
            return null;
        }

        // Reconstruction du chemin à partir des prédécesseurs
        ArrayList<CentreDeSante> path = new ArrayList<>();
        CentreDeSante centre = destination;

        while (centre != null) {
            path.add(centre);
            centre = previousCentre.get(centre);
        }

        Collections.reverse(path);

        return new Pair<>(path, reliability.get(destination));
    }

    /**
     * Obtient le centre de santé correspondant à un numéro donné.
     *
     * @param number Numéro du centre de santé.
     * @return Centre de santé correspondant au numéro, ou null si non trouvé.
     */
    public CentreDeSante getCentreDeSantebyNumber(int number) {
        for (CentreDeSante centre : centreDeSanteList) {
            if (centre.getId().equals("S" + number)) {
                return centre;
            }
        }
        return null;
    }

    /**
     * Obtient le centre de santé correspondant à une chaîne donnée.
     *
     * @param str Chaîne d'identification du centre de santé.
     * @return Centre de santé correspondant à la chaîne, ou null si non trouvé.
     */
    public CentreDeSante getCentreDeSantebyString(String str) {
        for (CentreDeSante centre : centreDeSanteList) {
            if (centre.getId().equals(str)) {
                return centre;
            }
        }
        return null;
    }

    /**
     * Obtient les voisins directs d'un centre de santé.
     *
     * @param centre Centre de santé pour lequel obtenir les voisins.
     * @return Liste des voisins directs du centre de santé.
     */
    public List<CentreDeSante> getVoisin(CentreDeSante centre) {
        // Crée une liste pour stocker les voisins
        List<CentreDeSante> voisins = new ArrayList<>();

        // Parcoure toutes les routes à partir du centre de santé
        for (Route route : routesMap.get(centre)) {
            // Ajoute le centre de santé de destination à la liste des voisins
            voisins.add(route.getDestination());
        }

        return voisins;
    }

    /**
     * Obtient les voisins directs d'un centre de santé d'un type spécifié.
     *
     * @param centre Centre de santé pour lequel obtenir les voisins de type
     * spécifié.
     * @param type Type spécifié des voisins à rechercher.
     * @return Liste des voisins directs du centre de santé qui correspondent au
     * type spécifié.
     */
    public List<CentreDeSante> getVoisinParType(CentreDeSante centre, String type) {
        // Crée une nouvelle liste pour stocker les voisins de type spécifié
        List<CentreDeSante> voisinsParType = new ArrayList<>();

        // Parcourir toutes les routes du centre de santé
        for (Route route : routesMap.get(centre)) {
            // Vérifie si le type du centre de destination de la route est égal au type spécifié
            if (route.getDestination().getType().equals(type)) {
                // Ajoute le centre de destination à la liste des voisins de type spécifié
                voisinsParType.add(route.getDestination());
            }
        }

        // Retourne la liste des voisins de type spécifié
        return voisinsParType;
    }

    /**
     * Obtient les voisins à une distance de 2 du centre de santé donné.
     *
     * @param centre Centre de santé pour lequel obtenir les voisins à 2
     * distances.
     * @return Liste des voisins à 2 distances du centre de santé donné.
     */
    public List<CentreDeSante> getVoisinA2Distances(CentreDeSante centre) {
        List<CentreDeSante> result = new ArrayList<>();

        // On obtient d'abord les voisins directs du centre donné
        List<CentreDeSante> voisinsDirects = getVoisin(centre);

        for (CentreDeSante voisinDirect : voisinsDirects) {
            // Pour chaque voisin direct, on obtient ses voisins
            List<CentreDeSante> voisinsDeVoisinDirect = getVoisin(voisinDirect);

            for (CentreDeSante voisinDeVoisinDirect : voisinsDeVoisinDirect) {
                // Si le voisin du voisin n'est pas le centre initial et n'est pas déjà dans la liste de résultats, on l'ajoute
                if (!voisinDeVoisinDirect.equals(centre) && !result.contains(voisinDeVoisinDirect)) {
                    result.add(voisinDeVoisinDirect);
                }
            }
        }

        return result;
    }

    /**
     * Supprime un centre de santé de la liste des centres de santé.
     *
     * @param centre Centre de santé à supprimer.
     */
    public void removeCentreDeSante(CentreDeSante centre) {
        // Supprime le centre de santé de la liste des centres de santé
        centreDeSanteList.remove(centre);
    }

    /**
     * Supprime toutes les routes associées à un centre de santé.
     *
     * @param centre Centre de santé pour lequel supprimer les routes.
     */
    public void removeRoutes(CentreDeSante centre) {
        // Supprime toutes les routes associées à ce centre de santé
        routesMap.remove(centre);

        // Supprime ce centre de santé en tant que destination de toutes les autres routes
        for (ArrayList<Route> routes : routesMap.values()) {
            routes.removeIf(route -> route.getDestination().equals(centre));
        }
    }

    /**
     * Ajoute une nouvelle route à un centre de santé existant.
     *
     * @param depart Centre de santé de départ pour la nouvelle route.
     * @param route Nouvelle route à ajouter.
     */
    public void addRoute(CentreDeSante depart, Route route) {
        // Si le centre de départ n'a pas encore de routes associées, crée une nouvelle liste de routes
        if (!routesMap.containsKey(depart)) {
            routesMap.put(depart, new ArrayList<>());
        }

        // Ajoute la nouvelle route à la liste des routes du centre de départ
        routesMap.get(depart).add(route);
    }

    /**
     * Vérifie les fichiers et assigne les chemins par défaut si nécessaire.
     *
     * @param adjacencePath Chemin du fichier de liste d'adjacence.
     * @param successeursPath Chemin du fichier de liste de successeurs.
     * @return Tableau contenant les chemins des fichiers et l'indicateur de
     * fichier correct.
     */
    private String[] verifierFichiersEtAssignerCheminParDefaut(String adjacencePath, String successeursPath) {
        String adjacenceFichier;
        String successeurFichier;
        boolean fichierCorrect;

        if (adjacencePath == null || successeursPath == null || adjacencePath.length() == 0 || successeursPath.length() == 0) {
            adjacenceFichier = "assets/liste-adjacence.csv";
            successeurFichier = "assets/liste-successeurs.csv";
            System.out.println("La création du Graphe à partir du fichier n'a pas été effectuée.");
            System.out.println("Tentative de création du Graphe à partir des adresses par défaut");
            fichierCorrect = false;
        } else {
            System.out.println("Création du Graphe à partir des fichiers");
            adjacenceFichier = adjacencePath;
            successeurFichier = successeursPath;
            fichierCorrect = true;
        }

        return new String[]{adjacenceFichier, successeurFichier, String.valueOf(fichierCorrect)};
    }

    /**
     * Lit le contenu d'un fichier ligne par ligne.
     *
     * @param filePath Chemin du fichier.
     * @return Liste contenant les lignes du fichier.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    private List<String> lireFichier(String filePath) throws IOException {
        Scanner fileScanner = new Scanner(new FileReader(filePath));
        List<String> fileContent = new ArrayList<>();

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            fileContent.add(line);
        }

        return fileContent;
    }

    /**
     * Ajoute les centres de santé à partir des lignes d'adjacence fournies.
     *
     * @param ligneAdjaList Liste des lignes d'adjacence.
     */
    private void ajouterCentresDeSanteViaListe(List<String> ligneAdjaList) {
        boolean commencerRemplissage = false;
        for (String adline : ligneAdjaList) {
            if (adline.startsWith("S1")) {
                commencerRemplissage = true;
            }
            if (commencerRemplissage) {
                String[] ligneAdjaSeparee = adline.split(";");
                addCentreDeSante(ligneAdjaSeparee[0], ligneAdjaSeparee[1]);
            }
        }
    }

    /**
     * Ajoute les routes à partir des lignes d'adjacence et de successeurs
     * fournies.
     *
     * @param ligneAdjaList Liste des lignes d'adjacence.
     * @param ligneSuccList Liste des lignes de successeurs.
     */
    private void ajouterRoutesViaListe(List<String> ligneAdjaList, List<String> ligneSuccList) {
        boolean commencerRemplissage = false;
        int idLigne = 0;

        for (String ligneAdja : ligneAdjaList) {
            if (ligneAdja.startsWith("S1")) {
                commencerRemplissage = true;
            }
            if (commencerRemplissage) {
                String ligneSucc = ligneSuccList.get(idLigne);
                String[] ligneSuccSeparee = Arrays.copyOfRange(ligneSucc.split(";"), 1, ligneSucc.split(";").length);

                int[] intArray = new int[ligneSuccSeparee.length];
                for (int i = 0; i < ligneSuccSeparee.length; i++) {
                    intArray[i] = Integer.parseInt(ligneSuccSeparee[i]);
                }

                trierArrayDeChaine(intArray);

                String[] ligneAdjaSeparee = Arrays.copyOfRange(ligneAdja.split(";"), 2, ligneAdja.split(";").length);
                int idColonne = 0;

                for (String caseCsv : ligneAdjaSeparee) {
                    if (!caseCsv.equals("0")) {
                        CentreDeSante centre1 = getCentreDeSantebyString(ligneAdja.split(";")[0]);
                        if (idColonne < intArray.length) {
                            CentreDeSante centre2 = getCentreDeSantebyNumber(intArray[idColonne]);
                            String[] cellData = caseCsv.split(",");

                            int distance = Integer.parseInt(cellData[1].trim());
                            int duree = Integer.parseInt(cellData[2].trim());
                            double fiabilite = Double.parseDouble(cellData[0].trim()) * 10;

                            addRoute(centre1, centre2, distance, duree, fiabilite);
                        }
                        idColonne++;
                    }
                }
                idLigne++;
            }
        }
    }

    /**
     * Trie un tableau d'entiers de manière croissante.
     *
     * @param str Tableau d'entiers à trier.
     */
    private void trierArrayDeChaine(int[] str) {
        int size = str.length;
        for (int x = 0; x < size - 1; x++) {
            for (int y = x + 1; y < size; y++) {
                if (str[x] > str[y]) {
                    int tmp = str[x];
                    str[x] = str[y];
                    str[y] = tmp;
                }
            }
        }
    }

}
