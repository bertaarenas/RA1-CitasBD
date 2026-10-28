package org.digitech.filevault.dao;
import org.digitech.filevault.exception.GestorArchivosException;
import org.digitech.filevault.model.Cita;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class GestorSerial {

    private static final Path ARCHIVO_CITAS = Paths.get("data/citas/citas.dat");

    public static void guardarCitas(List<Cita> citas) throws GestorArchivosException {

        try (ObjectOutputStream salida = new ObjectOutputStream(
                Files.newOutputStream(ARCHIVO_CITAS))) {

            salida.writeObject(citas);

        }
        catch (IOException e) {
            throw new GestorArchivosException("No se pudieron guardar las citas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Cita> cargarCitas() throws GestorArchivosException {


        if (Files.notExists(ARCHIVO_CITAS)) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(ARCHIVO_CITAS))) {

            return (List<Cita>) entrada.readObject();

        }
        catch (ClassNotFoundException e) {
            throw new GestorArchivosException("El archivo contiene una clase desconocida: " + e.getMessage());

        }
        catch (IOException e) {
            throw new GestorArchivosException("No se pudieron leer las citas: " + e.getMessage());
        }
    }
}