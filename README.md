# REST_API
Pour télécharger le projet vous pouvez effectuer la commande :
```bash
$ git clone https://github.com/Bintousk/REST_API.git
```
dans votre terminal.
Vous pouvez aussi télécharger le format Zip .

Pour pouvoir Run le projet vous devez l'ouvrir dans un IDE comme Intellij ou Eclipse.

Ensuite vous devez aller dans dossier /src/main/java/myApi et Run le fichier MyApiApplication.java

Une fois le service démarrer , vous pouvez allez le consulter a travers le terminal.

Voici de exemple de commandes: 
pour consulter la liste de tous les utilisateurs 
```bash
$ curl -v localhost:8080/user
```

Pour obtenir le profil de l'utilisateur [1]
```bash
$ curl -v localhost:8080/user/1
```

Pour ajouter un utilisateur 
```bash
$ curl -v -X POST localhost:8080/user -H 'Content-Type:application/json' -d '{  
               "name":"votre nom",
               "email":"courriel",
               "phone":"514-555-5555",
               "credit": 100
}'
```
Pour modifier le nombre de credit d'un utilisateur
```bash
$ curl -v -X PATCH localhost:8080/user/1 '{"credit":7}'    
```

Pour mettre a jour le profil d'un utilisateur 
```bash
$ curl -v -X PUT localhost:8080/user/2 -H 'Content-Type:application/json' -d '{         
               "name":"nouveau nom",
               "email":"noucourriel",
               "phone":"514-999-9999",
               "credit": 1
}'
```
