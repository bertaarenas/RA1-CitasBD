package org.digitech.filevault.app;
import org.digitech.filevault.dao.GestorSerial;
import org.digitech.filevault.model.Cita;
import java.util.ArrayList;
import java.util.List;
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

            List<Cita> citas = new ArrayList<>();
            citas.add(new Cita(1, "Berta", "Dra.Martínez", "Cardiologia", "2026-10-28"));
            citas.add(new Cita(2 ,"Arnau","Dr.Gomez",));

            GestorSerial.guardarCitas(citas);

            System.out.println("--- Citas recuperadas del archivo ---");
            for (Cita c : GestorSerial.cargarCitas()) {
                System.out.println(c);
            }

            System.out.println("Eventos registrados correctamente");

        }
        catch (GestorArchivosException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}