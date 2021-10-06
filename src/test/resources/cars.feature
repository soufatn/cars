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