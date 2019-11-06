# Bank CLI

## Start the CLI

Make sure you enable snapchot repository in your `settings.xml`, if not add the following profile :
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <profiles>
        <profile>
            <id>spring</id>
            <repositories>
                <repository>
                    <id>spring-snapshots</id>
                    <name>Spring Snapshots</name>
                    <url>https://repo.spring.io/libs-snapshot-local</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>spring-milestones</id>
                    <name>Spring Milestones</name>
                    <url>https://repo.spring.io/libs-milestone-local</url>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>spring-releases</id>
                    <name>Spring Releases</name>
                    <url>https://repo.spring.io/release</url>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>spring-snapshots</id>
                    <name>Spring Snapshots</name>
                    <url>https://repo.spring.io/libs-snapshot-local</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </pluginRepository>
                <pluginRepository>
                    <id>spring-milestones</id>
                    <name>Spring Milestones</name>
                    <url>https://repo.spring.io/libs-milestone-local</url>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </pluginRepository>
            </pluginRepositories>
        </profile>
    </profiles>
</settings>
```

Then you can start the project with this command : `mvn clean spring-boot:run`

## Architecture

The Shell component are in `fr.unice.polytech.credirama.bank.cli.command` package
Each method are annotate with `@ShellMethod` that describe the command and it's parameter
There is one service `CrediramaService` that call the backend on different endpoint. This service is used by the shell component.