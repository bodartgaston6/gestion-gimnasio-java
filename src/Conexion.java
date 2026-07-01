import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // ========================================================================================
    // ELEGÍ TU FORMA DE ENTRAR A SQL SERVER (Descomentá la que uses y comentá la otra)
    // ========================================================================================

    // OPCIÓN A: Si usás "Windows Authentication" (Entrás a SSMS sin poner usuario ni clave)
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HeedFitDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    // OPCIÓN B: Si usás "SQL Server Authentication" (Ponés usuario 'sa' y una contraseña)
    // private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=HeedFitDB;encrypt=true;trustServerCertificate=true;";
    // private static final String USUARIO = "sa";
    // private static final String CONTRASENA = "tu_contraseña_aca";

    // ========================================================================================

    public static Connection conectar() {
        try {
            // Si usaste la OPCIÓN A (Windows), dejá esta línea así:
            Connection conexion = DriverManager.getConnection(URL);

            // Si usaste la OPCIÓN B (Usuario y Clave), borrá la línea de arriba y usá esta:
            // Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            System.out.println("✅ ¡Conexión exitosa a la base de datos HeedFitDB!");
            return conexion;

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con SQL Server:");
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Este main es solo temporal para hacer la prueba de conexión
    public static void main(String[] args) {
        // Asegurate de haber creado la base de datos HeedFitDB en SSMS antes de darle Play!
        conectar();
    }
}
