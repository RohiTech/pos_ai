import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaPrincipal extends JFrame {
    private JDesktopPane escritorio;
    private JLabel etiquetaUsuario;
    private JLabel etiquetaFechaHora;

    public VentanaPrincipal() {
        // Configuración de la ventana principal
        setTitle("Ventana Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear barra de menús
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemSalir);
        barraMenu.add(menuArchivo);
        setJMenuBar(barraMenu);

        // Crear barra de botones
        JToolBar barraBotones = new JToolBar();
        JButton boton1 = new JButton("Botón 1");
        JButton boton2 = new JButton("Botón 2");
        barraBotones.add(boton1);
        barraBotones.add(boton2);

        // Configurar barra de estado
        JPanel barraEstado = new JPanel();
        etiquetaUsuario = new JLabel("Usuario: admin");
        etiquetaFechaHora = new JLabel();
        barraEstado.setLayout(new FlowLayout(FlowLayout.LEFT));
        barraEstado.add(etiquetaUsuario);
        barraEstado.add(new JSeparator(JSeparator.VERTICAL));
        barraEstado.add(etiquetaFechaHora);

        // Crear contenedor para ventanas internas
        escritorio = new JDesktopPane();

        // Agregar componentes a la ventana principal
        add(barraBotones, BorderLayout.NORTH);
        add(escritorio, BorderLayout.CENTER);
        add(barraEstado, BorderLayout.SOUTH);

        // Configurar manejador de eventos para salir
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Configurar temporizador para actualizar la fecha y hora en la barra de estado
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFechaHora();
            }
        });
        timer.start();
    }

    private void actualizarFechaHora() {
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        etiquetaFechaHora.setText("Fecha y Hora: " + formatoFechaHora.format(new Date()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setVisible(true);
            }
        });
    }
}
