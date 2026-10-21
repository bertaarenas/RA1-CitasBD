package org.digitech.filevault.util;
import org.digitech.filevault.exception.GestorArchivosException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class GestorDirectorios {   // crea las carpetas y enseña la informacion

    // crea las carpetas del programa
    public static void inicializarEstructura() throws GestorArchivosException {

        Path logs = Paths.get("data/logs");
        Path backup = Paths.get("data/backup");
        Path citas = Paths.get("data/citas");

        try {
            // crea la carpeta, si existe no pasa nada
            Files.createDirectories(logs);
            Files.createDirectories(backup);
            Files.createDirectories(citas);

            System.out.println("Carpetas creadas correctamente");

        }
        catch (IOException e) {

            throw new GestorArchivosException("Error al crear las carpetas: " + e.getMessage());
        }
    }

    // metodo para enseña la información
    public static void leer (String carpeta) throws GestorArchivosException {

        // convertir texto en ruta
        Path ruta = Paths.get(carpeta);

        try {
            System.out.println("Ruta: " + ruta.toAbsolutePath());

            // Espacio libre del disco (en bytes)
            System.out.println("Espacio disponible: " + Files.getFileStore(ruta).getUsableSpace() + " bytes");

            // permisos
            System.out.println("Se puede leer: " + Files.isReadable(ruta));
            System.out.println("Se puede escribir: " + Files.isWritable(ruta));

            System.out.println("Ultima modificacion: " + Files.getLastModifiedTime(ruta));

        }
        catch (IOException e) {
            throw new GestorArchivosException("Error al leer la carpeta: " + e.getMessage());
        }
    }
}