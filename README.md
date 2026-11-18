# RA1 - Citas Médicas

## Fase 1: Presentación del Reto y Análisis de Requisitos
Se ha organizado el código en cuatro paquetes, y uno más para la excepción. Hemos separado el código en cada clase para que sea fácil saber dónde está cada cosa.

| **Paquete** | **Función** | **Clase** |
| --- | --- | --- |
| model | Clases que representan los datos | Cita |
| dao | Guardar y leer los datos en archivos | GestorSerial, AccesoDirectoCitas |
| util | Herramientas de apoyo | GestorDirectorios, GestorTexto, ConversorCSV |
| app | Programa principal | Main, MainApp |
| exception | Excepción propia | GestorArchivosException |

## Fase 2: gestión de directorios e inspección
En la Fase 2 hemos hecho que el programa cree por sí solo las carpetas donde guardará sus archivos y que pueda leerlas. Para ello hemos creado la clase GestorDirectorios, que usa Path y Files de java.nio.file. Su método inicializarEstructura() crea las carpetas data/logs, data/backup y data/citas con rutas que no dan error si ya existen. Su método leer() muestra por consola la ruta absoluta de una carpeta, el espacio disponible en el disco, los permisos de lectura y escritura y la fecha de última modificación.

## Fase 3: flujos de texto y registro de auditoría
En la Fase 3 hemos hecho que el programa apunte en un archivo todo lo que va pasando. Para ello hemos creado la clase GestorTexto. Su método registrarEvento() escribe una línea con la fecha, la hora, el tipo de evento y el mensaje, y la añade al final del archivo audit.log sin borrar lo que ya había. También se puede leer todo el log y buscar una palabra, y el programa nos enseña las líneas donde sale y cuántas hay.

## Fase 4: serialización de objetos
En la Fase 4 hemos hecho que el programa guarde las citas en un archivo y que luego las pueda recuperar. Para ello la clase Cita es Serializable, y la clase GestorSerial guarda y lee la lista de citas en data/citas/citas.dat. Además se pueden agregar, buscar, actualizar y eliminar citas sin perder las que ya estaban guardadas. Si el archivo no existe, el programa devuelve una lista vacía en vez de dar error.

## Fase 5: acceso aleatorio directo
En la Fase 5 hemos hecho que el programa pueda ir directamente a una cita dentro del archivo sin leer todas las anteriores. Para ello hemos creado la clase AccesoDirectoCitas, que guarda las citas en data/citas/citas.raf y hace que cada cita ocupe siempre el mismo espacio (100 bytes). Así sabemos dónde está cada una: la cita 1 está al principio, la 2 justo después, y así. Con esto podemos cambiar solo la fecha de una cita sin tocar el resto de sus datos.

## Fase 6: integración y pruebas
En la Fase 6 hemos juntado todo en un menú por consola, la clase MainApp, para poder probar todas las funciones desde un mismo sitio. Si algo falla, el programa avisa del error y sigue funcionando. También hemos creado la clase ConversorCSV, que guarda las citas en data/backup/citas.csv para poder abrirlas en un programa como Excel. La clase Main se ha dejado como una prueba rápida sin menú.
