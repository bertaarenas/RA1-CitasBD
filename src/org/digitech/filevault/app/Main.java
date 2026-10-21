package org.digitech.filevault.app;
import org.digitech.filevault.exception.GestorArchivosException;
import org.digitech.filevault.util.GestorDirectorios;
public class Main {

    public static void main(String[] args) {

        try {
            GestorDirectorios.inicializarEstructura();

            GestorDirectorios.leer("data/logs");

        } catch (GestorArchivosException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}