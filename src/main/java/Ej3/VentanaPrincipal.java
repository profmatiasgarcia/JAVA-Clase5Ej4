package Ej3;
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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton botonInicio, botonParada;
    private JScrollPane panelDesplazamiento;
    private JList lista;
    private DefaultListModel modeloLista;
    private JProgressBar barraProgreso;
    private GeneraPrimos generaPrimos;

    public VentanaPrincipal() {
        super("Multitarea Swing");
        this.panelDesplazamiento = new JScrollPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());
        this.botonInicio = this.construyeBoton("Iniciar");
        this.botonParada = this.construyeBoton("Parar");
        this.botonParada.setEnabled(false);
        this.barraProgreso = this.construyeBarraProgreso(0, 99);
        this.modeloLista = new DefaultListModel();
        this.lista = new JList(this.modeloLista);
        this.panelDesplazamiento.setViewportView(this.lista);
        this.getContentPane().add(this.panelDesplazamiento);
        this.pack();
        this.setVisible(true);
    }

    private JButton construyeBoton(String titulo) {
        JButton b = new JButton(titulo);
        b.setActionCommand(titulo);
        b.addActionListener(this);
        this.getContentPane().add(b);
        return b;
    }

    private JProgressBar construyeBarraProgreso(int min, int max) {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(min);
        progressBar.setMaximum(max);
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(true);
        this.getContentPane().add(progressBar);
        return progressBar;
    }

    public void actionPerformed(ActionEvent e) {
        if ("Iniciar".equals(e.getActionCommand())) {
            this.modeloLista.clear();
            this.botonInicio.setEnabled(false);
            this.botonParada.setEnabled(true);
            this.generaPrimos = new GeneraPrimos(this.modeloLista, this.barraProgreso, this.botonInicio,
                    this.botonParada);
            this.generaPrimos.execute();
        } else if ("Parar".equals(e.getActionCommand())) {
            this.botonInicio.setEnabled(true);
            this.botonParada.setEnabled(false);
            this.generaPrimos.cancel(true);
            this.generaPrimos = null;
        }
    }
}
