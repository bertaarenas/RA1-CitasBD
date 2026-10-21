package org.digitech.filevault.model;
import java.io.Serializable;
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String paciente;
    private String especialidad;
    private String fecha;

    public Cita(int id, String paciente, String especialidad, String fecha) {
        this.id = id;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Cita [ID=" + id + ", Paciente=" + paciente
                + ", Especialidad=" + especialidad + ", Fecha=" + fecha + "]";
    }
}