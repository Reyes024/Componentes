/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Buscador extends JPanel implements ActionListener {

    private JTextField txtBuscar;
    private JButton btn1;
    private JSeparator separator;
    private JPanel innerPanel;
    private JLabel lblResultado;
    private Color defaultTextColor = Color.GRAY;
    private String defaultText = "Buscar...";
    private int buttonWidth = 30; // Ancho deseado del botón
    private int buttonHeight = 30; // Alto deseado del botón

    public Buscador() {
        // Inicializar los componentes
        txtBuscar = new JTextField(20);
        btn1 = new JButton();

        // Cargar la imagen desde el paquete 'imagenes' y redimensionarla
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/find.png"));
        Image image = icon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        btn1.setIcon(resizedIcon);

        separator = new JSeparator();
        lblResultado = new JLabel();

        // Quitar bordes
        txtBuscar.setBorder(null);
        btn1.setBorder(null);
        separator.setBorder(null);

        // Establecer el mismo color de fondo
        Color backgroundColor = txtBuscar.getBackground();
        innerPanel = new JPanel();
        innerPanel.setBackground(backgroundColor);
        txtBuscar.setBackground(backgroundColor);
        btn1.setBackground(backgroundColor);

        // Configurar texto inicial y color
        txtBuscar.setText(defaultText);
        txtBuscar.setForeground(defaultTextColor);

        // Agregar listener para manejar el texto y color al ganar/perder foco
        txtBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals(defaultText)) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText(defaultText);
                    txtBuscar.setForeground(defaultTextColor);
                }
            }
        });

        // Agregar MouseListener al botón para cambiar tamaño al presionar
        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Redimensionar el ícono al presionar
                Image pressedImage = icon.getImage().getScaledInstance(buttonWidth + 5, buttonHeight + 5, Image.SCALE_SMOOTH);
                btn1.setIcon(new ImageIcon(pressedImage));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Restaurar el ícono al tamaño original
                btn1.setIcon(resizedIcon);
            }
        });

        // Vincular ActionListener al botón
        btn1.addActionListener(this);

        // Crear el GroupLayout para el innerPanel
        GroupLayout innerLayout = new GroupLayout(innerPanel);
        innerPanel.setLayout(innerLayout);
        innerLayout.setAutoCreateGaps(true);
        innerLayout.setAutoCreateContainerGaps(true);

        // Configurar el GroupLayout para el innerPanel
        innerLayout.setHorizontalGroup(
            innerLayout.createSequentialGroup()
                .addGroup(innerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(innerLayout.createSequentialGroup()
                        .addComponent(txtBuscar)
                        .addComponent(btn1))
                    .addComponent(separator)
                    .addComponent(lblResultado))
        );

        innerLayout.setVerticalGroup(
            innerLayout.createSequentialGroup()
                .addGroup(innerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar)
                    .addComponent(btn1))
                .addComponent(separator)
                .addComponent(lblResultado)
        );

        // Añadir el innerPanel al panel principal
        setLayout(new BorderLayout());
        add(innerPanel, BorderLayout.CENTER);
    }

    // Método para manejar la acción del botón de búsqueda
    @Override
    public void actionPerformed(ActionEvent evt) {
        String textoBuscar = txtBuscar.getText();

        if (textoBuscar.trim().isEmpty() || textoBuscar.equals("Buscar...")) {
            lblResultado.setText("Debe rellenar el espacio de búsqueda");
            lblResultado.setForeground(Color.RED);
            return;
        }

        escribirHistorialBusqueda(textoBuscar);
        buscarTextoEnArchivo(textoBuscar);
    }

    // Método para escribir en el historial de búsqueda
    private void escribirHistorialBusqueda(String textoBuscar) {
        File archivoHistorial = new File("historial de búsqueda.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoHistorial, true))) {
            writer.write(textoBuscar);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            lblResultado.setText("Error al escribir en el historial de búsqueda");
        }
    }

    // Método para buscar texto en el archivo de elementos de búsqueda
    private void buscarTextoEnArchivo(String textoBuscar) {
        File archivoElementos = new File("elementos de búsqueda.txt");
        if (!archivoElementos.exists()) {
            try {
                archivoElementos.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                lblResultado.setText("Error al crear el archivo de elementos de búsqueda");
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoElementos))) {
            String linea;
            boolean encontrado = false;
            while ((linea = reader.readLine()) != null) {
                if (linea.equals(textoBuscar)) { // Cambiado a equals para coincidencia exacta
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                lblResultado.setText("Texto encontrado: " + textoBuscar);
                lblResultado.setForeground(Color.GREEN);
            } else {
                lblResultado.setText("Texto no encontrado: " + textoBuscar);
                lblResultado.setForeground(Color.RED);
            }
            txtBuscar.setText("Buscar...");
            txtBuscar.setForeground(Color.GRAY);
        } catch (IOException e) {
            e.printStackTrace();
            lblResultado.setText("Error al leer el archivo de elementos de búsqueda");
        }
    }

    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Buscador Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        // Añadir el panel al marco
        frame.add(new Buscador());

        // Mostrar el marco
        frame.setVisible(true);
    }
}
