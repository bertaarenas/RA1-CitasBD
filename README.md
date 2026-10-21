# RA1 - Citas Médicas
## Fase 1: Presentación del Reto y Análisis de Requisitos
Se ha organizado el código en cuatro paquetes, y uno más para la excepción. Hemos separado el código en cada clase para que sea fácil saber dónde está cada cosa.


| **Paquete** | **Función** | **Clase** |
| --- | --- | --- |
| model | Clases que representan los datos | Cita |
| dao | Guardar y leer los datos en archivos | GestorSerial |
| util | Herramientas de apoyo | GestorDirectorios, GestorTexto |
| app | Programa principal | Main |
| exception | Excepción propia | GestorArchivosException |
