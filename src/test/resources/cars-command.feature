# language: fr
Fonctionnalité: Dupliquer une voiture

  Scénario: Mise à jour du prix
    Etant donné Les voitures suivantes
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
    Quand on met à jour le prix à 11000 de la voiture (1)
    Alors on reçoit un OK
    Et on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 11000 | Small    |

  Scénario: Mise à jour du prix impossible à cause d'un changement de catégorie
    Etant donné Les voitures suivantes
      | name   | price | category |
      | twingo | 10000 | Small    |
    Quand on met à jour le prix à 50000 de la voiture (1)
    Alors on reçoit un non modifié
    Et on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 10000 | Small    |

  Scénario: Dupliquer une voiture
    Etant donné Les voitures suivantes
      | name   | price | category |
      | twingo | 10000 | Small    |
      | espace | 55000 | Family   |
    Quand on duplique une "twingo" en "megane" à 25000€
    Alors on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 10000 | Small    |
      | megane | 25000 | Medium   |