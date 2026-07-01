import java.time.LocalDate; // Importamos la herramienta de fechas

public class Socio {
    public int id;
    public String nombre;
    public String apellido;
    public LocalDate fechaVencimiento; // NUEVO TIPO DE DATO

    public Socio(int idSocio, String nombreSocio, String apellidoSocio, int meses) {
        this.id = idSocio;
        this.nombre = nombreSocio;
        this.apellido = apellidoSocio;

        // ¡Acá está la magia! Tomamos la fecha de HOY y le sumamos los meses
        this.fechaVencimiento = LocalDate.now().plusMonths(meses);
    }
}