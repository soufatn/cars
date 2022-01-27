# language: fr
Fonctionnalité: Commandes concernant les clients

  Scénario: Création d'un client
    Quand on crée un nouveau client avec un email "test@mail.com"
    Alors on reçoit un Created pour le client
    Et on récupère les informations suivantes de la base client
      | email         |
      | test@mail.com |

  Scénario: Dupliquer un client
    Etant donné Les clients suivants
      | email          |
      | test1@mail.com |
    Quand on duplique un "test1@mail.com" en "test2@mail.com"
    Alors on reçoit un Created pour le client
    Et on récupère les informations suivantes de la base client
      | email          |
      | test1@mail.com |
      | test2@mail.com |
