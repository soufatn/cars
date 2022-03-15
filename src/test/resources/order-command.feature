# language: fr
Fonctionnalité: Commandes des voitures

  Scénario: Création d'une commande
    Etant donné Les clients suivants:
      | email         |
      | test@mail.com |
    Et Les voitures suivantes:
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
    Quand on crée une nouvelle commande
    Alors on reçoit un Created pour la commande
    Et on récupère les informations suivantes de la base commande
      | id | email         | idVoiture | price |
      | 1  | test@mail.com | 1         | 10000 |