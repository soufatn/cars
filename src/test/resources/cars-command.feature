# language: fr
Fonctionnalité: Dupliquer une voiture

  Scénario: Mise à jour du prix
    Etant donné Les voitures suivantes
      | id | name   | price |
      | 1  | twingo | 10000 |
    Quand on met à jour le prix à 11000 de la voiture (1)
    Alors on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 11000 | Small    |

  Scénario: Dupliquer une voiture
    Etant donné Les voitures suivantes
      | name   | price |
      | twingo | 10000 |
      | espace | 55000 |
    Quand on duplique une "twingo" en "megane" à 25000€
    Alors on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 10000 | Small    |
      | megane | 25000 | Medium   |