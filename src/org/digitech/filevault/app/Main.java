package org.digitech.filevault.app;

import org.digitech.filevault.exception.GestorArchivosException;
import org.digitech.filevault.util.GestorDirectorios;
import org.digitech.filevault.util.GestorTexto;

public class Main {

    public static void main(String[] args) {

        try {
            GestorDirectorios.inicializarEstructura();
            GestorDirectorios.leer("data/logs");

            GestorTexto.registrarEvento("INFO", "Sistema iniciado.");
            GestorTexto.registrarEvento("INFO", "Cita creada para la paciente Berta.");

            System.out.println("-- Contenido del log --");
            GestorTexto.leerLog();

            System.out.println("-- Busqueda de la palabra: Berta --");
            GestorTexto.buscarPalabra("Berta");

            System.out.println("Eventos registrados correctamente");

        }
        catch (GestorArchivosException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}