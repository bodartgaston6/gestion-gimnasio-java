# Proyecto HeedFit - Gestión de Gimnasio

Un sistema de gestión (CRUD) desarrollado en Java para aplicar conceptos de programación orientada a objetos, diseño de interfaces gráficas y conexión a bases de datos relacionales. 

El objetivo principal de este proyecto fue construir una aplicación de escritorio funcional, separando la lógica de negocio de la interfaz visual y asegurando la persistencia de los datos en un servidor real.

-- Qué hace el sistema
- Gestión de Socios: Permite registrar nuevos clientes, modificar sus datos, visualizar la lista completa y eliminarlos.
- Cálculo automático de vencimientos: Utiliza la API LocalDate de Java para calcular exactamente qué día vence la membresía del socio en base a los meses abonados.
- Interfaz Visual: La interacción del usuario se realiza a través de una interfaz gráfica desarrollada íntegramente con Java Swing.
-  Base de Datos: La información se almacena en SQL Server mediante consultas preparadas (PreparedStatement) a través de JDBC, lo que previene ataques de inyección SQL. 
- Validaciones: El sistema implementa manejo de excepciones (try-catch) para evitar cierres inesperados por errores de tipeo y valida a nivel de base de datos que no se puedan ingresar DNI duplicados.

-- Tecnologías utilizadas
- Lenguaje: Java 
- Interfaz Gráfica: Java Swing
- Base de Datos: Microsoft SQL Server
- Conectividad: JDBC (Microsoft SQL Server Driver)
- Entorno de Desarrollo: IntelliJ IDEA

## Instrucciones para ejecución local
Para clonar el repositorio y ejecutar el proyecto en un entorno local:

1. Tener instalado Microsoft SQL Server y habilitar el protocolo TCP/IP por el puerto 1433 desde el Configuration Manager.
2. Crear una base de datos denominada HeedFitDB y ejecutar el script para la tabla Socios (columnas: DNI, Nombre, Apellido y FechaVencimiento).
3. Configurar el driver JDBC en el IDE, asegurando la inclusión del archivo .jar y el .dll correspondiente para la autenticación integrada de Windows.
4. Ejecutar la clase Main.java para iniciar la aplicación.
