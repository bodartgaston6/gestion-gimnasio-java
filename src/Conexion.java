import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HeedFitDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a la base de datos");
            return conexion;

        } catch (SQLException e) {
            System.out.println("Error al conectar con SQL Server:");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        conectar();
    }
}
