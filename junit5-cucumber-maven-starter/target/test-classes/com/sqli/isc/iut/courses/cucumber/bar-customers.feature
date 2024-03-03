Feature: Gestion des places assises au bar le Juste

  Background:
    Given le bar le Juste a seulement 10 places assises

  Scenario: Refus d'entrée lorsque le bar est complet
    Given il y a déjà 9 personnes dans le bar
    When Mr Pignon et Mr Leblanc essaient d'entrer
    Then ils se font refuser le droit d'entrée
    And le bar affiche complet scenario un

  Scenario: Bar complet et commande de cocktails
    Given il y a déjà 8 personnes dans le bar
    When la personne derrière Mr Pignon et Mr Leblanc essaie d'entrer
    Then elle se fait refuser le droit d'entrée
    And le bar affiche complet scenario deux
    When Mr Pignon et Mr Leblanc commandent chacun un cocktail du mois a 10 euros
    And Mr Leblanc paie l'ensemble
    Then à la fin de leur verre, la note est vérifiée
    And Mr Leblanc paie
    And Mr Pignon est heureux car ils n'ont consommé qu'un verre

  Scenario: Commandes de cocktails et paiements au bar
    Given il y a déjà 3 personnes dans le bar
    When Mr Pignon et Mr Leblanc commandent chacun un cocktail du mois à 10 euros
    And personne ne paie le verre de l'autre
    And à la fin de leur verre, ils vérifient chacun leur note
    And Mr Pignon paie
    And Mr Leblanc insiste pour payer un autre cocktail du mois
    And il commande donc 2 autres cocktails du mois pour sa note
    And à la fin de leur verre, Mr Leblanc vérifie la note et paie
    Then Mr Pignon est triste car il sait qu'au dessus d'un cocktail ce dernier va passer une très mauvaise nuit
