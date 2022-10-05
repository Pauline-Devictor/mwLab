# Middleware Lab

## Auteurs
- DEVICTOR Pauline
- PARIS Floriane

## Lancer le projet
- Lancez le server.Main.main() côté serveur puis le client.Main.main() côté client
- Rentrez le chiffre correspondant à l'action souhaitée 
- Vous avez le choix de créer un (ou plusieurs) comptes ou de vous connecter
- Une fois connecté, vous avez le choix de voir le catalogue, voir un film ou ajouter un film
- En selectionnant un film, vous avez le choix de voir les détails du film avant ou pas
- Si vous choisissez de voir les détails du film, le serveur décide aléatoirement (Une chance sur deux) de vous montrer le teaser du film

Il est également possible de lancer le serveur et le client sur des projet ou des ordinateurs séparés en conservant les mêmes noms de packages.
- Pour lancer sur des ordinateurs différents, il faut décommenter les lignes "distance" et commenter la ligne "local" côté client ainsi que rajouter l'adresse IP du serveur dans LocateRegistry.getRegistry(IP,Port);

## Explications du projet
- Dans le package Database nous avons des fichiers MovieData.csv et ClientData.csv afin de stocker les informations sur les comptes clients et les films ajoutés, les informations restent donc disponibles même après le redémarrage du serveur.
- Des erreurs sont affichées si on essaie de créer un compte dont le mail est déjà présent dans la base de donnée
- Il n'est pas possible d'avoir accès aux films tant que l'on n'est pas connecté
- Le client a la possibilité d'ajouter un film à la liste
