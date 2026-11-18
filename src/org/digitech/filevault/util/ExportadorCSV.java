package org.digitech.filevault.util;



import org.digitech.filevault.exception.GestorArchivosException;

import org.digitech.filevault.model.Cita;



import java.io.BufferedWriter;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.util.List;



public class ExportadorCSV {



    private static final Path ARCHIVO_CSV = Paths.get("data/backup/citas.csv");



    public static void exportarCitas(List<Cita> citas) throws GestorArchivosException {



        try (BufferedWriter escritor = Files.newBufferedWriter(

                ARCHIVO_CSV, StandardCharsets.UTF_8)) {



            escritor.write("id,paciente,medico,especialidad,fecha");

            escritor.newLine();



            for (Cita c : citas) {

                escritor.write(c.getId() + ","

                        + c.getPaciente() + ","

                        + c.getMedico() + ","

                        + c.getEspecialidad() + ","

                        + c.getFecha());

                escritor.newLine();

            }



        } catch (IOException e) {

            throw new GestorArchivosException("No se pudo exportar el CSV: " + e.getMessage());

        }

    }

}









