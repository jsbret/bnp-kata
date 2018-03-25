# bnp-kata

L'objectif de ce KATA est de refactorer l'application *App* afin de pouvoir effectuer à minima 100000 requêtes en moins de 3 minutes et garantir que la somme affichée dans la console en fin d'exécution doit toujours être égale à 0.

Dans le répertoire *server* à la racine, vous trouverez deux binaires permettant d'exécuter un serveur. Celui-ci écoute sur l'URL http://localhost:8081. Lors de l'envoi d'une requête GET du type *http://localhost:8081/request?value=105* le serveur retrourne *{"value":-105,"wait":174}*. Choisissez le binaire adapté à votre OS et exécuter le. Vous dévriez voir apparaitre dans la console le message suivant : *kata_server.go:108: Launch server on port 8081, you can request me !*

Exécuter le class App pour vérifier que tout marche bien. Et voilà c'est à vous de jouer !
