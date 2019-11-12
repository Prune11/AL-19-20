# AL-19-20

## Getting started

Tout d'abors, il faut éxecuter le service `manageEntrepriseAccounts` avec les commandes : `cd manageEntrepriseAccounts` puis `mvn clean package spring-boot:run`

Puis dans un autre terminal à la racine du projet exécuter la CLI Bank : `cd BankCLI` à partir de la racine du projet puis `mvn clean package spring-boot:run`
Puis dans un autre terminal à la racine du projet exécuter la CLI Merchant : ` cd MerchantCLI` puis `mvn clean package spring-boot:run`

## Pretty Dump

Le pretty dump peut etre demander sur la route `localhost:8081/prettyDump`, chaque appel vas créer un fichier texte avec un timestamp comme nom. Ce fichier vas être créer dans un dossier `prettydump` a la racine du projet, si il n'y est pas, il faurait le créer. (`mkdir prettydump`)

Sinon le dump est accessible depuis la CLI avec la command `dump` avec l'option `-p` pour formatter le json et l'option `-s` pour sauvegarder en fichier json.

## Comandes dans la CLI

Les commandes qu'on a éxecuter lors de notre démonstration ce trouve dans le fichier `demo_commands.txt` a la racine du projet, sinon les voicis: 

```
# Bank
create-client Michel
create-account 1 WOOD
create-client Roger
creat-account 2 WOOD
# Merchant
balance 2
transaction 2 1 5 TRANSFER
balance 2
transaction 0 2 5 TRANSFER
balance 2
fees 2
show-contract 2
# Bank
update-contract 2 STONE
# Merchant
transaction 2 1 5 TRANSFER
balance 2
fees 2
# Bank
dump -p
```

Les commandes après `# Bank` sont a éxecuter dans la CLI Bank et les commandes après `# Merchant` sont a éxecuter dans le CLI Merchant

Sinon nos CLI propose des commandes intégré, comme `help` pour voir toute les commandes disponible avec une documentation détaillée pour chacune d'entre elles.
