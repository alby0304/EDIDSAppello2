@echo off
rem Script per compilare ed eseguire il progetto ListAdapter

rem Compilazione del codice sorgente
echo Compilazione del codice sorgente...
javac -cp ".;cldc-1.1.jar;jUnit/junit-4.13.2.jar;jUnit/hamcrest-core-1.3.jar" myAdapter/*.java myTest/*.java

rem Verifica se la compilazione è stata completata con successo
if %errorlevel% equ 0 (
    echo Compilazione completata con successo.

    rem Esecuzione dei test
    echo Esecuzione dei test...
    java -cp ".;cldc-1.1.jar;jUnit/junit-4.13.2.jar;jUnit/hamcrest-core-1.3.jar" myTest.TestRunner

    rem Chiedi all'utente se desidera eliminare tutti i file .class
    set /p risposta="Vuoi eliminare tutti i file .class dopo l'esecuzione? (s/n) "

    rem Controlla la risposta dell'utente
    if /I "%risposta%"=="s" (
        rem Se l'utente ha risposto si, elimina tutti i file .class
        echo Eliminazione di tutti i file .class...
        for /r %%f in (*.class) do del /F /Q "%%f"
        echo Tutti i file .class sono stati eliminati.
    ) else (
        rem Se l'utente ha risposto no, non fare nulla
        echo Non vengono eliminati i file .class.
    )
) else (
    echo Errore durante la compilazione del codice sorgente. Impossibile eseguire i test.
)