# language: fr
Fonctionnalité: Lister les voitures

  Scénario: Lister les voitures avec leur catégorie
    Etant donné Les voitures suivantes
      | name   | price |
      | twingo | 10000 |
      | espace | 55000 |
    Quand on liste les voitures
    Alors on récupère les informations suivantes
      | name   | category |
      | twingo | Small    |
      | espace | Family   |

  Scénario: Mise à jour du prix
    Etant donné Les voitures suivantes
      | name   | price |
      | twingo | 10000 |
    Quand on met à jour le prix à 11000
    Alors on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 11000 | Small    |

  Scénario: Mise à jour du prix impossible à cause d'un changement de catégorie
    Etant donné Les voitures suivantes
      | name   | price |
      | twingo | 10000 |
    Quand on met à jour le prix à 50000
    Alors on récupère les informations suivantes de la base
      | name   | price | category |
      | twingo | 10000 | Small    |