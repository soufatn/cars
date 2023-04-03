# language: fr
Fonctionnalité: Commandes des voitures

  Scénario: Création d'une commande
    Etant donné Les clients suivants:
      | email         |
      | test@mail.com |
    Et Les voitures suivantes:
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
    Quand on crée une nouvelle commande avec email "test@mail.com" et idCar 1
    Alors on reçoit un Created pour la commande
    Et on récupère les informations suivantes de la base commande
      | id | email         | idVoiture | price | orderDate  |
      | 1  | test@mail.com | 1         | 10000 | 10/01/2011 |

  Scénario: Création d'une deuxième commande par le même client avec 10% de réduction
    Etant donné Les clients suivants:
      | email         |
      | test@mail.com |
    Et Les voitures suivantes:
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
    Et les commandes suivantes:
      | email         | idVoiture | price | orderDate  |
      | test@mail.com | 1         | 10000 | 10/01/2011 |
    Quand on crée une nouvelle commande avec email "test@mail.com" et idCar 1
    Alors on reçoit un Created pour la commande
    Et on récupère les informations suivantes de la base commande
      | id | email         | idVoiture | price | orderDate  |
      | 1  | test@mail.com | 1         | 10000 | 10/01/2011 |
      | 2  | test@mail.com | 1         |  9000 | 12/03/2012 |

  Scénario: Création d'une commande récente (< 1 an) par le même client avec 20% de réduction
    Etant donné Les clients suivants:
      | email         |
      | test@mail.com |
    Et Les voitures suivantes:
      | id | name   | price | category |
      | 1  | twingo | 10000 | Small    |
    Et les commandes suivantes:
      | email         | idVoiture | price | orderDate |
      | test@mail.com | 1         | 10000 | 10/01/2011 |
      | test@mail.com | 1         |  9000 | 12/03/2012 |
    Quand on crée une nouvelle commande avec email "test@mail.com" et idCar 1
    Alors on reçoit un Created pour la commande
    Et on récupère les informations suivantes de la base commande
      | id | email         | idVoiture | price | orderDate |
      | 1  | test@mail.com | 1         | 10000 | 10/01/2011 |
      | 2  | test@mail.com | 1         |  9000 | 12/03/2012 |
      | 3  | test@mail.com | 1         |  8000 | 08/05/2012 |