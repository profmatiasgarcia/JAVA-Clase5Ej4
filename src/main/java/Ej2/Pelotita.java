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

public class Pelotita extends Thread {

    private JPanel panel;
    private static final int tamanoXPelotita = 10;
    private static final int tamanoYPelotita = 10;
    private int posicionX = 0;
    private int posicionY = 0;
    private int incrementoX = 2;
    private int incrementoY = 2;
    private Color color;

    public Pelotita(JPanel b, Color c) {
        panel = b;
        color = c;
    }

    // Dibuja la pelotita la primera vez
    public void dibujarPelotita() {
        Graphics g = panel.getGraphics();
        g.setColor(color);
        g.fillOval(posicionX, posicionY, tamanoXPelotita, tamanoYPelotita);
        g.dispose();
    }

    public void mover() {
        Graphics g = panel.getGraphics();
        g.setXORMode(panel.getBackground());
        g.setColor(color);
        // Pintamos en las anitiguas coordenadas, borrando de ese modo la
        // pelotita,
        // Ya que estamos en modo XOR
        g.fillOval(posicionX, posicionY, tamanoXPelotita, tamanoYPelotita);
        // Calculamos las nuevas coordenadas
        posicionX += incrementoX;
        posicionY += incrementoY;
        Dimension d = panel.getSize();
        // Si la coordenada x es menor que 0 invertimos el incremento en la
        // coordenada x
        if (posicionX < 0) {
            posicionX = 0;
            incrementoX = -incrementoX;
        }
        // Si la pelotita se sale del panel por la derecha hacemos que "rebote"
        // hacia la izquierda
        if (posicionX + tamanoXPelotita >= d.width) {
            posicionX = d.width - tamanoXPelotita;
            incrementoX = -incrementoX;
        }
        // Si la coordenada y es menor que 0 invertimos el incremento en la
        // coordenada y
        if (posicionY < 0) {
            posicionY = 0;
            incrementoY = -incrementoY;
        }
        // Si la pelotita se sale del panel por abajo hacemos que "rebote"
        // hacia arriba
        if (posicionY + tamanoYPelotita >= d.height) {
            posicionY = d.height - tamanoYPelotita;
            incrementoY = -incrementoY;
        }
        g.fillOval(posicionX, posicionY, tamanoXPelotita, tamanoYPelotita);
        g.dispose();
    }

    public void rebotar() {
        dibujarPelotita();
        // La pelotita se moverá 1000 veces
        for (int i = 1; i <= 1000; i++) {
            mover();
            try {
                // Esta sentencia duerme durente 5 milisegundos el thread,
                // Evitando que la pelotita se mueva demasiado rápido
                Thread.currentThread().sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Cuerpo del Thread. No hay ningún problem por invocar a otros métodos
    // desde el
    public void run() {
        // Dibujamos la pelotita
        dibujarPelotita();
        // La movemos 10000 veces
        for (int i = 1; i <= 10000; i++) {
            mover();
            // Entre movimiento y movimiento detenemos el Thread 1 milisegundo,
            // así la pelotita no se moverá tan rápido. Para ello empleamos el
            // método sllep de la clase Thread, que puede lanzar una excepción,
            // por lo que hemos de invocar este método dentro de un try catch.
            try {
                sleep(1);
            } catch (InterruptedException ex) {
            }
        }
    }

}
