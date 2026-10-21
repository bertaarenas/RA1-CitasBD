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

##  Fase 2: gestión de directorios e inspección
En la Fase 2 hemos hecho que el programa cree por sí solo las carpetas donde guardará sus archivos y que pueda leerlas. Para ello hemos creado la clase GestorDirectorios, que usa Path y Files de java.nio.file. Su método inicializarEstructura() crea las carpetas data/logs, data/backup y data/citas con rutas que no dan error si ya existen. Su método leer() muestra por consola la ruta absoluta de una carpeta, el espacio disponible en el disco, los permisos de lectura y escritura y la fecha de última modificación. 

