import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;

    public VentanaLogin() {
        // Configuración de la ventana
        setTitle("Inicio de Sesión");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        JLabel etiquetaContraseña = new JLabel("Contraseña:");

        campoUsuario = new JTextField(20);
        campoContraseña = new JPasswordField(20);

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");

        // Configuración del diseño de la ventana
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Añadir componentes a la ventana
        add(etiquetaUsuario);
        add(campoUsuario);
        add(etiquetaContraseña);
        add(campoContraseña);
        add(botonIniciarSesion);

        // Configurar el manejador de eventos para el botón
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para verificar las credenciales en la base de datos
                String usuario = campoUsuario.getText();
                String contraseña = new String(campoContraseña.getPassword());

                if (verificarCredenciales(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Inicio de sesión exitoso");
                    // Aquí puedes abrir la nueva ventana del sistema o realizar otras acciones
                } else {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean verificarCredenciales(String usuario, String contraseña) {
        // Lógica para verificar las credenciales en la base de datos
        String query = "SELECT * FROM usuarios WHERE nombre_usuario = '?' AND contraseña = '?'";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement declaracion = conexion.prepareStatement(query)) {

            declaracion.setString(1, usuario);
            declaracion.setString(2, contraseña);

            try (ResultSet resultado = declaracion.executeQuery()) {
                return resultado.next(); // Si hay al menos una fila, las credenciales son válidas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaLogin ventanaLogin = new VentanaLogin();
                ventanaLogin.setVisible(true);
            }
        });
    }
}
