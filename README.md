# Java Chat Locale

Questa è una semplicissima applicazione client-server scritta in Java per chattare all'interno di una rete locale (LAN). Ad esempio, puoi usarla per chattare con un tuo coinquilino che è collegato alla stessa rete Wi-Fi.

## Prerequisiti

Entrambi i computer (il tuo e quello del tuo coinquilino) devono:
1. Essere connessi alla **stessa rete** (es. stesso Wi-Fi del router di casa).
2. Avere **Java** installato (versione 11 o superiore). Puoi verificarlo aprendo il Terminale e scrivendo:
   ```bash
   java -version
   ```

## Istruzioni (Per Te - Che farai da Server)

### 1. Trova il tuo indirizzo IP locale
Per fare in modo che il tuo coinquilino possa collegarsi al tuo computer, deve conoscere il tuo **indirizzo IP locale**.
Su macOS, puoi trovarlo in questo modo:
1. Apri il **Terminale**.
2. Scrivi questo comando e premi Invio:
   ```bash
   ipconfig getifaddr en0
   ```
   *(Nota: Se sei collegato via cavo ethernet, potresti dover usare `en1` anziché `en0` o guardare in Preferenze di Sistema > Rete).*
3. **Appuntati l'indirizzo IP** che compare a schermo (es. `192.168.1.50`).

### 2. Compila i file
Sempre dal Terminale, spostati nella cartella in cui si trovano questi file e scrivi:
```bash
javac ChatServer.java ChatClient.java
```
*(Questo creerà i file `.class` necessari per eseguire i programmi).*

### 3. Avvia il Server
Sempre nella stessa cartella, avvia il Server scrivendo:
```bash
java ChatServer
```
Il Terminale ti comunicherà che il Server è in attesa di una connessione sulla porta `5000`.

---

## Istruzioni (Per il tuo Coinquilino - Che farà da Client)

Il tuo coinquilino dovrà avere una copia di questi file (o almeno del file `ChatClient.java` compilato in `ChatClient.class`).

### 1. Compilare (se non l'ha già fatto)
Sul suo computer, aprendo il Terminale nella cartella dei file, dovrà scrivere:
```bash
javac ChatClient.java
```

### 2. Connettersi a te
Dovrà dirti "Qual è il tuo IP?" e tu gli dirai l'IP che hai trovato prima (es. `192.168.1.50`).
Lui dovrà scrivere sul suo Terminale:
```bash
java ChatClient 192.168.1.50
```
*(Ovviamente sostituendo l'indirizzo con il tuo vero IP).*

---

## Utilizzo della Chat

Appena il tuo coinquilino preme Invio per collegarsi, sul tuo Terminale vedrai che si è connesso.
A questo punto potete semplicemente **scrivere i messaggi e premere Invio** per inviarli!

Per uscire, vi basta chiudere il Terminale o premere `CTRL + C`.