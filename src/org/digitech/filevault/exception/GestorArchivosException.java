package org.digitech.filevault.exception;
public class GestorArchivosException extends Exception {

    public GestorArchivosException(String mensaje) {
        super("-- GESTOR ARCHIVOS ERROR ---> " + mensaje);
    }
}