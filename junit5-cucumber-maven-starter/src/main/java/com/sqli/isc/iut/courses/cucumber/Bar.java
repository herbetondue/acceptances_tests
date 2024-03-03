package com.sqli.isc.iut.courses.cucumber;

import java.util.HashMap;
import java.util.Map;

public class Bar {
    private final int nombreDePlacesAssises;
    private int nombreDePersonnesDansLeBar;
    private final Map<String, Double> commandes;
    private final Map<String, Double> paiements;

    public Bar(int nombreDePlacesAssises) {
        this.nombreDePlacesAssises = nombreDePlacesAssises;
        this.nombreDePersonnesDansLeBar = 0;
        this.commandes = new HashMap<>();
        this.paiements = new HashMap<>();
    }

    // Méthode pour gérer l'entrée des personnes dans le bar
    public boolean entrerAuBar(int nombreDePersonnes) {
        if (nombreDePersonnes > 0) {
            int placesRestantes = this.nombreDePlacesAssises - this.nombreDePersonnesDansLeBar;
            if (placesRestantes >= nombreDePersonnes) {
                this.nombreDePersonnesDansLeBar += nombreDePersonnes;
                return true; // Entrée autorisée
            } else {
                return false; // Entrée refusée car le bar est complet
            }
        } else {
            return false; // Nombre de personnes invalide
        }
    }

    // Méthode pour gérer la sortie des personnes du bar
    public void quitterLeBar(int nombreDePersonnes) {
        if (nombreDePersonnes > 0 && nombreDePersonnes <= this.nombreDePersonnesDansLeBar) {
            this.nombreDePersonnesDansLeBar -= nombreDePersonnes;
        }
    }

    // Méthode pour vérifier si le bar est complet
    public boolean isBarComplet() {
        return this.nombreDePersonnesDansLeBar >= this.nombreDePlacesAssises;
    }

    // Méthode pour obtenir le nombre de personnes actuellement présentes dans le bar
    public int getNombreDePersonnesDansLeBar() {
        return nombreDePersonnesDansLeBar;
    }

    // Méthodes pour gérer les commandes et les paiements
    public void commanderCocktail(String personne, double montant) {
        commandes.put(personne, montant);
    }

    // Méthode pour obtenir la note d'une personne
    public double getNote(String personne) {
        return commandes.getOrDefault(personne, 0.0);
    }

    // Méthode pour obtenir le montant payé par une personne
    public void payer(String personne, double montant) {
        paiements.put(personne, montant);
    }

    // Méthode pour obtenir le montant payé par une personne
    public void ajouterCommande(String personne, double montant) {
        double noteActuelle = commandes.getOrDefault(personne, 0.0);
        commandes.put(personne, noteActuelle + montant);
    }
}
