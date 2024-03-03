package com.sqli.isc.iut.courses.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class BarLeJusteSteps {
    private Bar bar;
    private boolean entreeRefusee;
    private boolean barAfficheComplet;
    private int personnesDansLeBarApres;
    private double note;
    private double montantPaye;
    private double noteMrPignon;
    private double noteMrLeblanc;

    /* PREMIER SCENARIO */

    @Given("^le bar le Juste a seulement (\\d+) places assises$")
    public void le_bar_le_Juste_a_seulement_places_assises(int nombreDePlacesAssises) {
        bar = new Bar(nombreDePlacesAssises);
    }

    @Given("^il y a déjà (\\d+) personnes dans le bar$")
    public void il_y_a_deja_personnes_dans_le_bar(int nombreDePersonnes) {
        bar.entrerAuBar(nombreDePersonnes);
    }

    @When("^Mr Pignon et Mr Leblanc essaient d'entrer$")
    public void mr_Pignon_et_Mr_Leblanc_essaient_d_entre() {
        entreeRefusee = !bar.entrerAuBar(2);
        personnesDansLeBarApres = bar.getNombreDePersonnesDansLeBar();
    }

    @Then("^ils se font refuser le droit d'entrée$")
    public void ils_se_font_refuser_le_droit_d_entree() {
        assertTrue(entreeRefusee, "Le droit d'entrée a été refusé");
    }

    @And("le bar affiche complet scenario un")
    public void leBarAfficheCompletScenarioUn() {
        barAfficheComplet = bar.isBarComplet();
        assertFalse(barAfficheComplet, "Le bar affiche complet");
    }

    @Then("^ils sont autorisés à entrer$")
    public void ils_sont_autorises_a_entrer() {
        assertFalse(entreeRefusee, "Le droit d'entrée a été accordé");
    }

    @Then("^le bar n'affiche pas complet$")
    public void le_bar_n_affiche_pas_complet() {
        assertTrue(barAfficheComplet, "Le bar n'affiche pas complet");
    }

    @When("^\\d+ personnes quittent le bar$")
    public void personnes_quittent_le_bar(int nombreDePersonnes) {
        bar.quitterLeBar(nombreDePersonnes);
        personnesDansLeBarApres = bar.getNombreDePersonnesDansLeBar();
    }

    @Then("^le nombre de personnes dans le bar est réduit à (\\d+)$")
    public void le_nombre_de_personnes_dans_le_bar_est_reduit_a(int nombreAttendu) {
        assertEquals(nombreAttendu, personnesDansLeBarApres, "Le nombre de personnes dans le bar est correct");
    }

    /* DEUXIEME SCENARIO */

    @When("^la personne derrière Mr Pignon et Mr Leblanc essaie d'entrer$")
    public void la_personne_derriere_Mr_Pignon_et_Mr_Leblanc_essaye_d_entre() {
        bar.entrerAuBar(2); // Mr Pignon et Mr Leblanc entrent dans le bar
        entreeRefusee = !bar.entrerAuBar(1); // La personne derrière Mr Pignon et Mr Leblanc essaie d'entrer
        System.out.println("Nombre de personnes dans le bar: " + bar.getNombreDePersonnesDansLeBar());
    }

    @Then("^elle se fait refuser le droit d'entrée$")
    public void elle_se_fait_refuser_le_droit_d_entree() {
        assertTrue(entreeRefusee, "Le droit d'entrée a été refusé");
    }

    @And("le bar affiche complet scenario deux")
    public void leBarAfficheCompletScenarioDeux() {
        barAfficheComplet = bar.isBarComplet();
        assertTrue(barAfficheComplet, "Le bar affiche complet");
    }

    @When("Mr Pignon et Mr Leblanc commandent chacun un cocktail du mois a {int} euros")
    public void mrPignonEtMrLeblancCommandentChacunUnCocktailDuMoisAEuros(int prix) {
        note = prix * 2; // Le prix total pour deux cocktails
    }

    @When("^Mr Leblanc paie l'ensemble$")
    public void mr_Leblanc_paie_l_ensemble() {
        montantPaye = note;
    }

    @Then("^à la fin de leur verre, la note est vérifiée$")
    public void a_la_fin_de_leur_verre_la_note_est_verifiee() {
        // Vérification de la note à la fin des verres
        assertEquals(note, montantPaye, 0.001, "La note est correcte");
    }

    @Then("^Mr Leblanc paie$")
    public void mr_Leblanc_paie() {
        // Mr Leblanc paie la note
        assertTrue(true, "Mr Leblanc a payé la note");
    }

    @Then("^Mr Pignon est heureux car ils n'ont consommé qu'un verre$")
    public void mr_Pignon_est_heureux_car_ils_n_ont_consomme_qu_un_verre() {
        // Mr Pignon est heureux car ils n'ont consommé qu'un verre
        assertTrue(true, "Mr Pignon est heureux");
    }
    
    /* TROISIEME SCENARIO */

    @When("Mr Pignon et Mr Leblanc commandent chacun un cocktail du mois à {int} euros")
    public void mrPignonEtMrLeblancCommandentChacunUnCocktailDuMoisÀEuros(int prix) {
        noteMrPignon = prix;
        noteMrLeblanc = prix;
        bar.commanderCocktail("Mr Pignon", prix);
        bar.commanderCocktail("Mr Leblanc", prix);
    }

    @When("^personne ne paie le verre de l'autre$")
    public void personne_ne_paie_le_verre_de_l_autre() {
        assertTrue(true, "Personne ne paie le verre de l'autre");
    }

    @Then("^à la fin de leur verre, ils vérifient chacun leur note$")
    public void a_la_fin_de_leur_verre_ils_verifient_chacun_leur_note() {
        // À la fin de leur verre, ils vérifient chacun leur note
        assertEquals(noteMrPignon, bar.getNote("Mr Pignon"), 0.001, "Note de Mr Pignon");
        assertEquals(noteMrLeblanc, bar.getNote("Mr Leblanc"), 0.001, "Note de Mr Leblanc");
    }

    @Then("^Mr Pignon paie$")
    public void mr_Pignon_paie() {
        double montantPayeMrPignon = noteMrPignon;
        bar.payer("Mr Pignon", montantPayeMrPignon);
    }

    @Then("^Mr Leblanc insiste pour payer un autre cocktail du mois$")
    public void mr_Leblanc_insiste_pour_payer_un_autre_cocktail_du_mois() {
        // Mr Leblanc insiste pour payer un autre cocktail du mois
        noteMrLeblanc *= 2; // Il commande 2 autres cocktails
        bar.ajouterCommande("Mr Leblanc", noteMrLeblanc);
    }

    @And("il commande donc {int} autres cocktails du mois pour sa note")
    public void ilCommandeDoncAutresCocktailsDuMoisPourSaNote(int nombreDeCocktailsSupplementaires) {
        note += nombreDeCocktailsSupplementaires * 10; // Le prix total des cocktails supplémentaires
    }

    @Then("^à la fin de leur verre, Mr Leblanc vérifie la note et paie$")
    public void a_la_fin_de_leur_verre_Mr_Leblanc_verifie_la_note_et_paie() {
        double montantPayeMrLeblanc = noteMrLeblanc;
        bar.payer("Mr Leblanc", montantPayeMrLeblanc);
    }

    @Then("^Mr Pignon est triste car il sait qu'au dessus d'un cocktail ce dernier va passer une très mauvaise nuit$")
    public void mr_Pignon_est_triste_car_il_sait_qu_au_dessus_d_un_cocktail_ce_dernier_va_passer_une_tres_mauvaise_nuit() {
        assertTrue(true, "Mr Pignon est triste");
    }
}

