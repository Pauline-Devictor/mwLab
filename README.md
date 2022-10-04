# Middleware Lab

## Auteurs
DEVICTOR Pauline
PARIS Floriane

## Lancer le projet
- Lancez le server.Main.main() côté serveur puis le client.Main.main() côté client
- Rentrez le chiffre correspondant à l'action souhaitée 
- Vous avez le choix de créer un (ou plusieurs) comptes ou de vous connecter
- Une fois connecté, vous avez le choix de voir le catalogue, voir un film ou ajouter un film

## Explications du projet
- Dans le package Database nous avons des fichiers MovieData.csv et ClientData.csv afin de stocker les informations sur les comptes clients et les films ajoutés, les informations restent donc disponibles même après le redémarrage du serveur.
- Des erreurs sont affichées si on essaie de créer un compte dont le mail est déjà présent dans la base de donnée
- Il n'est pas possible d'avoir accès aux films tant que l'on n'est pas connecté
- Le client a la possibilité d'ajouter un film à la liste
