package com.empresa.util;
import com.empresa.exception.GestorArchivosException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class GestorDirectorios {

    public static void inicializarEstructura() throws GestorArchivosException {

        Path rutaInformes = Paths.get("clinica/informes");
        Path rutaCitas = Paths.get("clinica/citas");
        Path rutaLogs = Paths.get("clinica/logs");

        try {

            Files.createDirectories(rutaInformes);
            Files.createDirectories(rutaCitas);
            Files.createDirectories(rutaLogs);

            System.out.println("Estructura de carpetas verificada con exito.");

        }
        catch (IOException e) {
            throw new GestorArchivosException("No se pudieron crear las carpetas: " + e.getMessage());
        }
    }
}