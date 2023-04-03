# language: fr
Fonctionnalité: Lister les voitures

  Scénario: Afficher toutes les voitures
    Etant donné Les voitures suivantes
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
      | 2  | espace | 55000 | Family   |
    Quand on affiche toutes les voitures
    Alors on récupère les voitures suivantes du body
      | id | name   | category |
      | 1  | twingo | Small    |
      | 2  | espace | Family   |

  Scénario: Afficher une voiture par nom
    Etant donné Les voitures suivantes
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
      | 2  | espace | 55000 | Family   |
    Quand on affiche une "twingo"
    Alors on récupère les informations suivantes du body
      | id | name   | category |
      | 1  | twingo | Small    |
