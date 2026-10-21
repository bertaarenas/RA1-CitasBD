package com.empresa.exception;
public class GestorArchivosException extends Exception {

    public GestorArchivosException(String mensaje) {
        super("-- GESTOR ARCHIVOS ERROR ---> " + mensaje);
    }
}