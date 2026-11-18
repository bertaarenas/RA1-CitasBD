package org.digitech.filevault.app;

import org.digitech.filevault.dao.AccesoDirectoCitas;
import org.digitech.filevault.dao.GestorSerial;
import org.digitech.filevault.exception.GestorArchivosException;
import org.digitech.filevault.model.Cita;
import org.digitech.filevault.util.ExportadorCSV;
import org.digitech.filevault.util.GestorDirectorios;
import org.digitech.filevault.util.GestorTexto;

import java.util.List;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) {


        try (Scanner teclado = new Scanner(System.in)) {


            try {

                GestorDirectorios.inicializarEstructura();

            } catch (GestorArchivosException e) {

                System.out.println("Error al crear las carpetas: " + e.getMessage());

                return;

            }


            int opcion = -1;


            while (opcion != 0) {


                mostrarMenu();

                opcion = leerNumero(teclado, "Elige una opcion: ");


                try {

                    switch (opcion) {


                        case 1:

                            GestorDirectorios.leer("data/logs");

                            break;


                        case 2:

                            System.out.print("Mensaje del evento: ");

                            String mensaje = teclado.nextLine();

                            GestorTexto.registrarEvento("INFO", mensaje);

                            System.out.println("Evento registrado");

                            break;


                        case 3:

                            System.out.println("--- Contenido del log ---");

                            GestorTexto.leerLog();

                            break;


                        case 4:

                            System.out.print("Palabra a buscar: ");

                            String palabra = teclado.nextLine();

                            GestorTexto.buscarPalabra(palabra);

                            break;


                        case 5:

                            anadirCita(teclado);

                            break;


                        case 6:

                            System.out.println("--- Citas guardadas ---");

                            List<Cita> guardadas = GestorSerial.cargarCitas();

                            if (guardadas.isEmpty()) {

                                System.out.println("No hay citas guardadas");

                            }

                            for (Cita c : guardadas) {

                                System.out.println(c);

                            }

                            break;


                        case 7:

                            AccesoDirectoCitas.guardarTodas(GestorSerial.cargarCitas());

                            System.out.println("Citas copiadas al archivo de acceso directo");

                            break;


                        case 8:

                            int numeroMostrar = leerNumero(teclado, "Numero de cita (0 es la primera): ");

                            AccesoDirectoCitas.mostrarCita(numeroMostrar);

                            break;


                        case 9:

                            int numeroCambiar = leerNumero(teclado, "Numero de cita (0 es la primera): ");

                            System.out.print("Nueva fecha (por ejemplo 2026-12-15): ");

                            String nuevaFecha = teclado.nextLine();

                            AccesoDirectoCitas.modificarFecha(numeroCambiar, nuevaFecha);

                            break;


                        case 10:

                            ExportadorCSV.exportarCitas(GestorSerial.cargarCitas());

                            System.out.println("Citas exportadas a data/backup/citas.csv");

                            break;


                        case 0:

                            System.out.println("Hasta luego");

                            break;


                        default:

                            System.out.println("Opcion no valida");

                    }


                } catch (GestorArchivosException e) {

                    System.out.println("Error: " + e.getMessage());

                }

            }

        }

    }


    private static void mostrarMenu() {

        System.out.println();

        System.out.println("===== GESTOR DE CITAS =====");

        System.out.println("1. Inspeccionar la carpeta de logs");

        System.out.println("2. Registrar un evento en el log");

        System.out.println("3. Ver el log completo");

        System.out.println("4. Buscar una palabra en el log");

        System.out.println("5. Anadir una cita");

        System.out.println("6. Ver las citas guardadas");

        System.out.println("7. Copiar las citas al archivo de acceso directo");

        System.out.println("8. Mostrar una cita (acceso directo)");

        System.out.println("9. Cambiar la fecha de una cita (acceso directo)");

        System.out.println("10. Exportar las citas a CSV");

        System.out.println("0. Salir");

    }


    private static int leerNumero(Scanner teclado, String pregunta) {

        while (true) {

            System.out.print(pregunta);

            try {

                return Integer.parseInt(teclado.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Eso no es un numero, prueba otra vez");

            }

        }

    }


    private static void anadirCita(Scanner teclado) throws GestorArchivosException {


        int id = leerNumero(teclado, "Id: ");

        System.out.print("Paciente: ");

        String paciente = teclado.nextLine();

        System.out.print("Medico: ");

        String medico = teclado.nextLine();

        System.out.print("Especialidad: ");

        String especialidad = teclado.nextLine();

        System.out.print("Fecha (por ejemplo 2026-11-05): ");

        String fecha = teclado.nextLine();


        List<Cita> citas = GestorSerial.cargarCitas();

        citas.add(new Cita(id, paciente, medico, especialidad, fecha));

        GestorSerial.guardarCitas(citas);


        GestorTexto.registrarEvento("INFO", "Cita creada para el paciente " + paciente);

        System.out.println("Cita guardada");

    }

}







