import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    public static void main(String[] args) {
        Gimnasio miGimnasio = new Gimnasio();

        JFrame ventana = new JFrame("HeedFit - Gestión de Socios");
        ventana.setSize(600, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // 1. PANEL DEL FORMULARIO (Arriba)
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelFormulario.add(new JLabel("Número de DNI:"));
        JTextField campoDni = new JTextField();
        panelFormulario.add(campoDni);

        panelFormulario.add(new JLabel("Nombre (Solo para registrar/modificar):"));
        JTextField campoNombre = new JTextField();
        panelFormulario.add(campoNombre);

        panelFormulario.add(new JLabel("Apellido (Solo para registrar/modificar):"));
        JTextField campoApellido = new JTextField();
        panelFormulario.add(campoApellido);

        panelFormulario.add(new JLabel("Meses que abona (Solo para registrar/modificar):"));
        JTextField campoMeses = new JTextField();
        panelFormulario.add(campoMeses);

        ventana.add(panelFormulario, BorderLayout.NORTH);

        // 2. ÁREA DE RESULTADOS (Centro)
        JTextArea areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaResultado);
        ventana.add(scroll, BorderLayout.CENTER);

        // 3. NUEVO PANEL DE BOTONES (Abajo)
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton botonRegistrar = new JButton("Registrar");
        JButton botonMostrar = new JButton("Ver Todos");
        JButton botonModificar = new JButton("Modificar"); // NUEVO
        JButton botonEliminar = new JButton("Eliminar");   // NUEVO

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonMostrar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);

        ventana.add(panelBotones, BorderLayout.SOUTH);

        // =========================================================
        // LÓGICA DE LOS BOTONES
        // =========================================================

        // BOTÓN REGISTRAR
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(campoDni.getText());
                    String nombre = campoNombre.getText();
                    String apellido = campoApellido.getText();
                    int meses = Integer.parseInt(campoMeses.getText());

                    Socio nuevo = new Socio(dni, nombre, apellido, meses);
                    miGimnasio.registrarSocio(nuevo);
                    JOptionPane.showMessageDialog(ventana, "Proceso de registro finalizado. Hacé click en 'Ver Todos' para confirmar.");

                    // Limpiamos cajas
                    campoDni.setText(""); campoNombre.setText(""); campoApellido.setText(""); campoMeses.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventana, "Error: El DNI y los Meses deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // BOTÓN MOSTRAR
        botonMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaResultado.setText(miGimnasio.obtenerListadoSocios());
            }
        });

        // BOTÓN MODIFICAR
        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Para modificar, necesitamos todos los datos de las cajas
                    int dni = Integer.parseInt(campoDni.getText());
                    String nombre = campoNombre.getText();
                    String apellido = campoApellido.getText();
                    int meses = Integer.parseInt(campoMeses.getText());

                    // Llamamos al método que ya tenías armado en el Backend
                    miGimnasio.modificarSocio(dni, nombre, apellido, meses);
                    JOptionPane.showMessageDialog(ventana, "Se intentó modificar el DNI " + dni + ".\nHacé click en 'Ver Todos' para verificar.");

                    campoDni.setText(""); campoNombre.setText(""); campoApellido.setText(""); campoMeses.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventana, "Para modificar, llená TODOS los campos. El DNI y los Meses deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // BOTÓN ELIMINAR
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Para eliminar, SOLO necesitamos el DNI
                    int dni = Integer.parseInt(campoDni.getText());

                    // Mostramos un cartel de confirmación antes de borrar
                    int confirmacion = JOptionPane.showConfirmDialog(ventana, "¿Seguro que querés eliminar el DNI " + dni + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        miGimnasio.eliminarSocio(dni);
                        JOptionPane.showMessageDialog(ventana, "Proceso de eliminación ejecutado.");
                        areaResultado.setText(miGimnasio.obtenerListadoSocios()); // Actualizamos la lista automáticamente
                        campoDni.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ventana, "Para eliminar, solo escribí el número de DNI en su caja.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Hacemos visible la ventana
        ventana.setVisible(true);
    }
}