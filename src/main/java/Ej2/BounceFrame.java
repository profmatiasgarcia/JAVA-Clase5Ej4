package Ej2;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2017 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * JFrame sobre el cual dibujaremos
 */
public class BounceFrame extends JFrame {

    private JPanel panelDibujar;

    public BounceFrame() {
        setSize(500, 400);
        // Gestión de los eventos de ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = getContentPane();
        panelDibujar = new JPanel();
        contentPane.add(panelDibujar, BorderLayout.CENTER);
        JPanel panelSur = new JPanel();

        JButton botonAnimar = new JButton("Animar");
        panelSur.add(botonAnimar);
        botonAnimar.addActionListener(new ActionListener() {
            // Cuando el usuario pulse estebotón dibujamos la pelotita y la
            // hacemo rebotar
            public void actionPerformed(ActionEvent evt) {
                Pelotita b = new Pelotita(panelDibujar, Color.black);
                // Le ponemos una prioridad baja a esta pelotita
                b.setPriority(Thread.MIN_PRIORITY);
                // Una vez creada la pelotita (El Thread) la hacemos rebotar
                b.start();
            }
        });

        JButton botonExpress = new JButton("Express");
        panelSur.add(botonExpress);
        botonExpress.addActionListener(new ActionListener() {
            // Cuando el usuario pulse este botón dibujamos la pelotita y la
            // hacemo rebotar
            public void actionPerformed(ActionEvent evt) {
                Pelotita b = new Pelotita(panelDibujar, Color.red);
                // Le ponemos una prioridad normal a esta pelotita
                b.setPriority(Thread.NORM_PRIORITY);
                // Una vez creada la pelotita (El Thread) la hacemos rebotar
                b.start();
            }
        });

        JButton botonDetener = new JButton("Detener");
        panelSur.add(botonDetener);
        botonDetener.addActionListener(new ActionListener() {
            // Al pulsar este botón detenemos la animación y terminamos la
            // máquina Virtual.
            // Como sólo emplemos un thread comprobaremos que el botón no
            // responde
            // hasta que la pelotita para de moverse.
            public void actionPerformed(ActionEvent evt) {
                panelDibujar.setVisible(false);
                System.exit(0);
            }
        });
        contentPane.add(panelSur, "South");
    }

}
