[![Build Status](https://cloud.drone.io/api/badges/Prune11/AL-19-20/status.svg)](https://cloud.drone.io/Prune11/AL-19-20)
# AL-19-20

## Getting started

Tout d'abord, il faut lancer nos services docker en lançant après avoir fait un mvn clean package, puis un docker compose up --build à la racine docker-compose run start_dependencies
Puis dans un autre terminal à la racine du projet exécuter la CLI Bank : `cd BankCLI` à partir de la racine du projet puis `mvn clean package spring-boot:run`
Puis dans un autre terminal à la racine du projet exécuter la CLI Merchant : ` cd MerchantCLI` puis `mvn clean package spring-boot:run`

## Pretty Dump

Le dump est accessible depuis la CLI avec la commande `dump` avec l'option `-p` pour formatter le json et l'option `-s` pour sauvegarder en fichier json.

## Tests d'intégration 

Pour lancer les tests d'intégration, il suffit de lancer mvn verify.

## Commandes dans la CLI

Les commandes ci dessous sont celles présentées lors de nos démonstrations : 

### POC
```
# Bank
create-client Michel
create-account 1 WOOD
create-client Roger
create-account 2 WOOD
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

### Finale

```
# Bank
create-client Michel
create-account 1 WOOD
create-client Roger
create-account 2 WOOD

# Merchant
transaction 1 2 100.50 TRANSFER
transaction 1 2 10.50 TRANSFER
transaction 1 2 10.50 TRANSFER
transaction 1 2 10.50 TRANSFER
transaction 1 2 10.50 TRANSFER
transaction 1 2 10.50 TRANSFER
transaction 1 2 10.50 TRANSFER
fees 2
simulation 2
```


Les commandes après `# Bank` sont à exécuter dans la CLI Bank et les commandes après `# Merchant` sont à exécuter dans le CLI Merchant

Sinon nos CLI proposent des commandes intégrées, comme `help` pour voir toutes les commandes disponibles avec une documentation détaillée pour chacune d'entre elles.
