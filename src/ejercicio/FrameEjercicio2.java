package ejercicio;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.*;

public class FrameEjercicio2 extends JFrame implements ActionListener{
    JTextField txfNumerosPulsados;
    JButton btnReset;

    // Declaración de elementos del menú
    JMenuBar menu;
    JMenu mnuArchivo, mnuMovil, mnuOtros;
    JMenuItem mnuGrabar, mnuLeer, mnuReset, mnuSalir, mnuAcercaDe; 

    int x = 100, y = 10; // Creo variables para controlar las coordenadas de los botones
    String[] botones = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "0", "*"}; // Creo un array con el texto que debe contener cada botón
    ArrayList<JButton> teclasTelefono = new ArrayList<>(); // Creo una colección para guardar los JButton creados para el teléfono

    public FrameEjercicio2() {
        super("Tema 9 Ejercicio 2");
        setLayout(null);
        setFocusable(true);


        class MouseHandler extends MouseAdapter { // Creo la clase MouseHandler con el adaptador

            @Override
            public void mouseEntered(MouseEvent e) { // Cambio el color de fondo cuando está sobre un botón que no ha sido pulsado
                if (((JButton) e.getSource()).getBackground() != Color.GRAY) {
                    ((JButton) e.getSource()).setBackground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) { // Cambio el color de fondo cuando sale de un botón que no ha sido pulsado
                if (((JButton) e.getSource()).getBackground() != Color.GRAY) {
                    ((JButton) e.getSource()).setBackground(null);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) { // Cambio el color de fondo cuando pulsa un botón
                ((JButton) e.getSource()).setBackground(Color.GRAY);
            }

        }

        class KeyHandler extends KeyAdapter { // Creo la clase KeyHandler con el adaptador

            @Override
            public void keyTyped(KeyEvent e) {
                for (int i = 0; i < teclasTelefono.size(); i++) {
                    if(Character.toString(e.getKeyChar()).equals(teclasTelefono.get(i).getText())){ // Comparo la tecla pulsada con los botones del teléfono
                        teclasTelefono.get(i).setBackground(Color.GRAY); // Si coincide, cambio el color de fondo del botón
                    }

                }

                try { //Creo un try-catch por si la tecla pulsada no es un número, para que no me de error y de paso lo aviso por consola
                    if(Integer.parseInt(Character.toString(e.getKeyChar())) >= 0 && Integer.parseInt(Character.toString(e.getKeyChar())) <=9){
                        if(txfNumerosPulsados.getText().equals("")){ // Si el TextField está vacío, no pongo una coma al principio
                            txfNumerosPulsados.setText(Character.toString(e.getKeyChar()));
                        }
                        else{
                            txfNumerosPulsados.setText(txfNumerosPulsados.getText() + "," + Character.toString(e.getKeyChar()));
                        }
                    }              
                } catch (Exception e1) {
                    System.err.println("La tecla pulsada no es un número");
                }
            } 

        }

        MouseHandler mh = new MouseHandler(); // Creo el MouseHandler
        KeyHandler kh = new KeyHandler(); // Creo el KeyHandler
        addKeyListener(kh); // Añado el KeyListener para controlar los eventos de teclado


        // Muestro la barra de menú y sus elementos en el formulario
        // Menú Archivo
        mnuGrabar = new JMenuItem("Grabar");
        mnuGrabar.setMnemonic('G');
        mnuGrabar.addActionListener(this);

        mnuLeer = new JMenuItem("Leer");
        mnuLeer.setMnemonic('L');
        mnuLeer.addActionListener(this);

        mnuArchivo = new JMenu("Archivo");
        mnuArchivo.setMnemonic('A');
        mnuArchivo.add(mnuGrabar);
        mnuArchivo.add(mnuLeer);

        // Menú Móvil
        mnuReset = new JMenuItem("Reset");
        mnuReset.setMnemonic('R');
        mnuReset.addActionListener(this);

        mnuSalir = new JMenuItem("Salir");
        mnuSalir.setMnemonic('S');
        mnuSalir.addActionListener(this);

        mnuMovil = new JMenu("Movil");
        mnuMovil.setMnemonic('M');
        mnuMovil.add(mnuReset);
        mnuMovil.addSeparator();
        mnuMovil.add(mnuSalir);

        // Menú Otros
        mnuAcercaDe = new JMenuItem("Acerca De");
        mnuAcercaDe.addActionListener(this);

        mnuOtros = new JMenu("Otros");
        mnuOtros.setMnemonic('O');
        mnuOtros.add(mnuAcercaDe);

        // Barra de Menú
        menu = new JMenuBar();
        menu.add(mnuArchivo);
        menu.add(mnuMovil);
        menu.add(mnuOtros);
        this.setJMenuBar(menu);


        for (int i = 0; i < botones.length; i++) {
            if (i % 3 == 0) { // Para que cada fila sea de 3 botones, cada 3 botones aumento la coordenada Y y devuelvo la coordenada X a su valor inicial
                y += 30;
                x = 100;
            }

            JButton btnTelefono = new JButton(botones[i]);
            btnTelefono.setSize(btnTelefono.getPreferredSize());
            btnTelefono.setLocation(x, y);
            btnTelefono.addMouseListener(mh); // Añado el MouseHandler a todos los botones
            add(btnTelefono);
            x += 45; // Cada vez que añado un nuevo botón, aumento la coordenada X

            teclasTelefono.add(btnTelefono);
        }

        // Creo el TextField
        txfNumerosPulsados = new JTextField(30);
        txfNumerosPulsados.setLocation(50, 200);
        txfNumerosPulsados.setSize(txfNumerosPulsados.getPreferredSize());
        txfNumerosPulsados.setEditable(false);
        txfNumerosPulsados.addActionListener(this);
        add(txfNumerosPulsados);

        // Creo el botón de resetear
        btnReset = new JButton("Reset");
        btnReset.setLocation(100, 250);
        btnReset.setSize(btnReset.getPreferredSize());
        btnReset.addActionListener(this);
        btnReset.addKeyListener(kh);
        add(btnReset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReset || e.getSource() == mnuReset){ // Acciones a realizar cuando se pulsa el botón reset o el MenuItem Reset
            txfNumerosPulsados.setText("");

            for (int i = 0; i < teclasTelefono.size(); i++) {
                teclasTelefono.get(i).setBackground(null);
            }
        }
        else if(e.getSource() == mnuGrabar){ // Acciones a realizar al pulsar la opción de menú "Grabar"
            // Elijo la carpeta personal del usuario para crear el nuevo archivo
            File archivoNumeros = new File(System.getProperty("user.home") + System.getProperty("file.separator") +  "T9Ejercicio2.txt");

            if(!archivoNumeros.exists()){ // Acciones a realizar si el archivo no existe
                try (PrintWriter f = new PrintWriter(System.getProperty("user.home") + System.getProperty("file.separator") + "T9Ejercicio2.txt")){ // Creo el archivo en un try-with-resources
                } catch (Exception e1) {
                    System.err.println("Error al crear el archivo de números");
                }
                
                try (PrintWriter escribirNumeros = new PrintWriter(new FileWriter(System.getProperty("user.home") + System.getProperty("file.separator") + "T9Ejercicio2.txt", true))){
                    escribirNumeros.printf("%s", txfNumerosPulsados.getText().split(",")[txfNumerosPulsados.getText().split(",").length-1]);
                } catch (IOException e2) {
                    System.err.println("Error de acceso al archivo de números");
                }    
            }
            else{ // Acciones a realizar si el archivo ya existe, y por lo tanto ya contiene números guardados
                try (PrintWriter escribirNumeros = new PrintWriter(new FileWriter(System.getProperty("user.home") + System.getProperty("file.separator") + "T9Ejercicio2.txt", true))){
                    escribirNumeros.printf("," + txfNumerosPulsados.getText().split(",")[txfNumerosPulsados.getText().split(",").length-1]);
                } catch (IOException e2) {
                    System.err.println("Error de acceso al archivo de números");
                }
            }
        }
        else if(e.getSource() == mnuAcercaDe){ // Acciones a realizar al pulsar la opción de menú "Acerca De"
            JOptionPane.showMessageDialog(null, "Este programa ha sido creado por el alumno Hugo Méndez González",
            "Acerca del programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}