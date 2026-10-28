package org.digitech.filevault.util;
import org.digitech.filevault.exception.GestorArchivosException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class GestorTexto {

    private static final Path ARCHIVO_LOG = Paths.get("data/logs/audit.log");

    public static void registrarEvento(String nivel, String mensaje) throws GestorArchivosException {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = LocalDateTime.now().format(formato);

        String linea = "[" + fechaHora + "] [" + nivel + "] " + mensaje;

        try (BufferedWriter escritor = Files.newBufferedWriter(
                ARCHIVO_LOG,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            escritor.write(linea);
            escritor.newLine();

        } catch (IOException e) {
            throw new GestorArchivosException("No se pudo escribir en el log: " + e.getMessage());
        }
    }

    public static void leerLog() throws GestorArchivosException {

        if (Files.notExists(ARCHIVO_LOG)) {
            throw new GestorArchivosException("El archivo de log todavia no existe");
        }

        try (BufferedReader lector = Files.newBufferedReader(ARCHIVO_LOG, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            throw new GestorArchivosException("No se pudo leer el log: " + e.getMessage());
        }

    }

    public static void buscarPalabra(String palabra) throws GestorArchivosException {

        if (Files.notExists(ARCHIVO_LOG)) {
            throw new GestorArchivosException("El archivo de log todavia no existe");
        }

        String buscada = palabra.toLowerCase();
        int encontradas = 0;

        try (BufferedReader lector = Files.newBufferedReader(ARCHIVO_LOG, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                if (linea.toLowerCase().contains(buscada)) {
                    System.out.println(linea);
                    encontradas++;
                }
            }

            System.out.println("Lineas encontradas: " + encontradas);

        }
        catch (IOException e) {
            throw new GestorArchivosException("No se pudo buscar en el log: " + e.getMessage());
        }
    }
}
