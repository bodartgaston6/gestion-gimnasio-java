import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Gimnasio {

    public Gimnasio() {
    }

    public void registrarSocio(Socio nuevoSocio) {
        String sql = "INSERT INTO Socios (DNI, Nombre, Apellido, FechaVencimiento) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, nuevoSocio.id);
            comando.setString(2, nuevoSocio.nombre);
            comando.setString(3, nuevoSocio.apellido);

            // ¡NUEVO! Convertimos el LocalDate de Java al Date que entiende SQL
            comando.setDate(4, java.sql.Date.valueOf(nuevoSocio.fechaVencimiento));

            comando.executeUpdate();
            System.out.println("-> ¡Socio guardado con vencimiento: " + nuevoSocio.fechaVencimiento + "!");

        } catch (SQLException e) {
            System.out.println("❌ Error: Es probable que el DNI ya exista en el sistema.");
        }
    }

    public String obtenerListadoSocios() {
        StringBuilder texto = new StringBuilder();
        texto.append("========= LISTA DE SOCIOS =========\n");

        String sql = "SELECT * FROM Socios";

        try (Connection conn = Conexion.conectar();
             PreparedStatement comando = conn.prepareStatement(sql);
             ResultSet tablaResultados = comando.executeQuery()) {

            boolean hayDatos = false;

            while (tablaResultados.next()) {
                hayDatos = true;
                int dni = tablaResultados.getInt("DNI");
                String nombre = tablaResultados.getString("Nombre");
                String apellido = tablaResultados.getString("Apellido");

                // ¡NUEVO! Leemos el Date de SQL y lo pasamos al LocalDate de Java
                java.sql.Date fechaSql = tablaResultados.getDate("FechaVencimiento");
                LocalDate vencimiento = fechaSql.toLocalDate();

                texto.append("DNI: ").append(dni)
                        .append(" | Apellido: ").append(apellido).append(", ").append(nombre)
                        .append(" | Vence el: ").append(vencimiento).append("\n"); // Ahora mostramos la fecha real
            }

            if (!hayDatos) {
                return "No hay socios registrados en la base de datos.";
            }

        } catch (SQLException e) {
            return "❌ Error al intentar leer la base de datos.";
        }
        return texto.toString();
    }

    public void modificarSocio(int idAModificar, String nuevoNombre, String nuevoApellido, int nuevosMeses) {
        String sql = "UPDATE Socios SET Nombre = ?, Apellido = ?, FechaVencimiento = ? WHERE DNI = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, nuevoNombre);
            comando.setString(2, nuevoApellido);

            // ¡NUEVO! Calculamos la nueva fecha de vencimiento a partir de hoy
            LocalDate nuevaFecha = LocalDate.now().plusMonths(nuevosMeses);
            comando.setDate(3, java.sql.Date.valueOf(nuevaFecha));

            comando.setInt(4, idAModificar);

            int filasAfectadas = comando.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("-> ¡Socio actualizado! Nuevo vencimiento: " + nuevaFecha);
            } else {
                System.out.println("-> No se encontró ningún socio con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al intentar modificar el socio.");
        }
    }

    public void eliminarSocio(int idAEliminar) {
        String sql = "DELETE FROM Socios WHERE DNI = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idAEliminar);
            int filasAfectadas = comando.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("-> ¡Socio eliminado de la Base de Datos!");
            } else {
                System.out.println("-> No se encontró ningún socio con ese DNI.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al intentar eliminar el socio.");
        }
    }
}