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

public class FrameEjercicio2 extends JFrame implements ActionListener {
    JTextField txfNumerosPulsados;
    

    public FrameEjercicio2() {
        super("Tema 9 Ejercicio 2");
        setLayout(null);

        class MouseHandler extends MouseAdapter { // Creo la clase MouseHandler con el adaptador

            @Override
            public void mouseEntered(MouseEvent e) {
                if(((JButton) e.getSource()).getBackground() != Color.GRAY){
                    ((JButton) e.getSource()).setBackground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(((JButton) e.getSource()).getBackground() != Color.GRAY){
                    ((JButton) e.getSource()).setBackground(null);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(Color.GRAY);
            }

        }

        MouseHandler mh = new MouseHandler(); // Creo el MouseHandler

        int x = 100, y = 10; // Creo variables para controlar las coordenadas de los botones
        String[] botones = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "0", "*"}; // Creo un array con el texto que debe contener cada botón

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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}