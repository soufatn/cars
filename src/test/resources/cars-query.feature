# language: fr
Fonctionnalité: Lister les voitures

  Scénario: Afficher toutes les voitures
    Etant donné Les voitures suivantes
      | name   | price | category |
      | twingo | 10000 | Small    |
      | espace | 55000 | Family   |
    Quand on affiche toutes les voitures
    Alors on récupère les voitures suivantes du body
      | name   | category |
      | twingo | Small    |
      | espace | Family   |

  Scénario: Afficher une voiture par nom
    Etant donné Les voitures suivantes
      | name   | price | category |
      | twingo | 10000 | Small    |
      | espace | 55000 | Family   |
    Quand on affiche une "twingo"
    Alors on récupère les informations suivantes du body
      | name   | category |
      | twingo | Small    |