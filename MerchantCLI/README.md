# Merchant CLI

## Start the CLI

Make sure the `manageEntrepriseAccounts` is launched.

Then you can start the project with this command : `mvn clean spring-boot:run`

## Architecture

The Shell fr.polytech.unice.credirama.prettydump.component are in `fr.unice.polytech.credirama.merchant.cli.command` package
Each method are annotate with `@ShellMethod` that describe the command and it's parameter.
There is one service `CrediramaService` that call the backend on different endpoint. This service is used by the shell fr.polytech.unice.credirama.prettydump.component.