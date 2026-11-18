package org.digitech.filevault.dao;
import org.digitech.filevault.exception.GestorArchivosException;
import org.digitech.filevault.model.Cita;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class AccesoDirectoCitas {

    private static final Path ARCHIVO = Paths.get("data/citas/citas_directo.dat");

    private static final int LONG_PACIENTE = 20;
    private static final int LONG_MEDICO = 20;
    private static final int LONG_FECHA = 10;

    private static final int TAMANO_REGISTRO = 104;
    private static final int POSICION_FECHA = 84;

    public static void guardarTodas(List<Cita> citas) throws GestorArchivosException {

        try (RandomAccessFile archivo = new RandomAccessFile(ARCHIVO.toString(), "rw")) {

            archivo.setLength(0);

            for (Cita c : citas) {
                archivo.writeInt(c.getId());
                escribirTexto(archivo, c.getPaciente(), LONG_PACIENTE);
                escribirTexto(archivo, c.getMedico(), LONG_MEDICO);
                escribirTexto(archivo, c.getFecha(), LONG_FECHA);
            }

        }
        catch (IOException e) {
            throw new GestorArchivosException("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    public static void mostrarCita(int numero) throws GestorArchivosException {

        try (RandomAccessFile archivo = new RandomAccessFile(ARCHIVO.toString(), "r")) {

            comprobarNumero(archivo, numero);

            long posicion = (long) numero * TAMANO_REGISTRO;
            archivo.seek(posicion);

            int id = archivo.readInt();
            String paciente = leerTexto(archivo, LONG_PACIENTE);
            String medico = leerTexto(archivo, LONG_MEDICO);
            String fecha = leerTexto(archivo, LONG_FECHA);

            System.out.println("Cita " + numero + " (empieza en el byte " + posicion + "): "
                    + "id=" + id + ", paciente=" + paciente
                    + ", medico=" + medico + ", fecha=" + fecha);

        } catch (IOException e) {
            throw new GestorArchivosException("No se pudo leer la cita: " + e.getMessage());
        }
    }

    public static void modificarFecha(int numero, String nuevaFecha) throws GestorArchivosException {

        try (RandomAccessFile archivo = new RandomAccessFile(ARCHIVO.toString(), "rw")) {

            comprobarNumero(archivo, numero);

            long posicion = (long) numero * TAMANO_REGISTRO + POSICION_FECHA;
            archivo.seek(posicion);
            escribirTexto(archivo, nuevaFecha, LONG_FECHA);

            System.out.println("Fecha de la cita " + numero + " cambiada a " + nuevaFecha);

        } catch (IOException e) {
            throw new GestorArchivosException("No se pudo modificar la fecha: " + e.getMessage());
        }
    }

    private static void comprobarNumero(RandomAccessFile archivo, int numero)
            throws IOException, GestorArchivosException {

        long posicion = (long) numero * TAMANO_REGISTRO;

        if (numero < 0 || posicion >= archivo.length()) {
            throw new GestorArchivosException("La cita " + numero + " no existe");
        }
    }

    private static void escribirTexto(RandomAccessFile archivo, String texto, int longitud)
            throws IOException {

        StringBuilder relleno = new StringBuilder(texto);

        while (relleno.length() < longitud) {
            relleno.append(' ');
        }

        relleno.setLength(longitud);
        archivo.writeChars(relleno.toString());
    }

    private static String leerTexto(RandomAccessFile archivo, int longitud) throws IOException {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            texto.append(archivo.readChar());
        }

        return texto.toString().trim();
    }
}



















 