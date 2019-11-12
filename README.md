# AL-19-20

## Getting started

Tout d'abord, il faut exécuter le service `manageEntrepriseAccounts` avec les commandes : `cd manageEntrepriseAccounts` puis `mvn clean package spring-boot:run`

Puis dans un autre terminal à la racine du projet exécuter la CLI Bank : `cd BankCLI` à partir de la racine du projet puis `mvn clean package spring-boot:run`
Puis dans un autre terminal à la racine du projet exécuter la CLI Merchant : ` cd MerchantCLI` puis `mvn clean package spring-boot:run`

## Pretty Dump

Le pretty dump peut être demandé sur la route `localhost:8081/prettyDump`, chaque appel va créer un fichier texte avec un timestamp comme nom. Ce fichier va être créer dans un dossier `prettydump` à la racine du projet, si il n'y est pas, il faudrait le créer. (`mkdir prettydump`)

Sinon le dump est accessible depuis la CLI avec la commande `dump` avec l'option `-p` pour formatter le json et l'option `-s` pour sauvegarder en fichier json.

## Commandes dans la CLI

Les commandes qu'on a exécuté lors de notre démonstration se trouvent dans le fichier `demo_commands.txt` à la racine du projet, sinon, vous pourrez les trouver ci dessous.
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

Les commandes après `# Bank` sont à exécuter dans la CLI Bank et les commandes après `# Merchant` sont à exécuter dans le CLI Merchant

Sinon nos CLI proposent des commandes intégrées, comme `help` pour voir toutes les commandes disponibles avec une documentation détaillée pour chacune d'entre elles.
