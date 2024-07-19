#!/bin/bash

# Script per compilare ed eseguire il progetto ListAdapter

# Compilazione del codice sorgente
echo "Compilazione del codice sorgente..."
javac -cp ".:cldc-1.1.jar:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myAdapter/*.java myTest/*.java

# Chiedi all'utente se desidera eliminare tutti i file .class
echo "Vuoi eliminare tutti i file .class dopo l'esecuzione? (si/no)"
read risposta

# Verifica se la compilazione è stata completata con successo
if [ $? -eq 0 ]; then
    echo "Compilazione completata con successo."
    # Esecuzione dei test
    echo "Esecuzione dei test..."
    java -cp ".:cldc-1.1.jar:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar" myTest.TestRunner
else
    echo "Errore durante la compilazione del codice sorgente. Impossibile eseguire i test."
fi

# Controlla la risposta dell'utente
if [ "$risposta" = "si" ]; then
    # Se l'utente ha risposto si, elimina tutti i file .class
    echo "Eliminazione di tutti i file .class..."
    find . -name "*.class" -type f -delete
    echo "Tutti i file .class sono stati eliminati."
else
    # Se l'utente ha risposto no, non fare nulla
    echo "Non vengono eliminati i file .class."

fi
