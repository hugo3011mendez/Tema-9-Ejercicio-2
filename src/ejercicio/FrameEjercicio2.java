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

    public FrameEjercicio2() {
        super("Tema 9 Ejercicio 2");
        setLayout(null);

        int x = 100, y = 10; // Creo variables para controlar las coordenadas de los botones

        for (int i = 1; i < 13; i++) {
            String numero = String.valueOf(i);

            if (i <= 9) {
                JButton numeroTelefono = new JButton(numero);
                numeroTelefono.setSize(numeroTelefono.getPreferredSize());
                numeroTelefono.setLocation(x, y);
                add(numeroTelefono);
                x += 45; // Cada vez que añado un nuevo botón, aumento la coordenada X

                if(i%3 == 0){ // Para que cada fila sea de 3 botones, cada 3 botones aumento la coordenada Y y devuelvo la coordenada X a su valor inicial
                    y += 30;
                    x = 100;
                }

            } else {
                switch (i) {
                    case 10:
                        JButton almohadillaTelefono = new JButton("#");
                        almohadillaTelefono.setSize(almohadillaTelefono.getPreferredSize());
                        almohadillaTelefono.setLocation(x, y);
                        add(almohadillaTelefono);
                        x += 45;
                    break;

                    case 11:
                        JButton numeroTelefono = new JButton("0");
                        numeroTelefono.setSize(numeroTelefono.getPreferredSize());
                        numeroTelefono.setLocation(x, y);
                        add(numeroTelefono);
                        x += 45;
                    break;

                    case 12:
                        JButton asteriscoTelefono = new JButton("*");
                        asteriscoTelefono.setSize(asteriscoTelefono.getPreferredSize());
                        asteriscoTelefono.setLocation(x, y);
                        add(asteriscoTelefono);
                        x += 45;
                    break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}