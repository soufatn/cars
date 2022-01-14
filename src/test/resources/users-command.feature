# language: fr
Fonctionnalité: Commandes concernant les clients

  Scénario: Création d'un client
    Quand on crée un nouveau client avec un email "test@mail.com"
    Alors on reçoit un Created pour le client
    Et on récupère les informations suivantes de la base client
      | email         |
      | test@mail.com |