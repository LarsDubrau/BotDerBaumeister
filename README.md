# BotDerBaumeister
 
 ## Projekt bauen (jar)
 ```
 $ mvn clean
 $ mvn compile assembly:single
 ```

## Config.json
Zum Ausführen wird eine Konfigurationsdatei benötigt, die wie folgt aufgebaut sein muss:
```json
{
    "botToken": "<Discord Application Token>",
    "tenorApiToken": "<Access token to tenor api>",
    "mongoDbConnectionString": "<connection string of mongo db>",
    "r6StatsApiToken": "<Access token to r6stats-API"
}
```

## Bot starten
```
$ java -jar BotDerBaumeister.jar /path/to/config.json
