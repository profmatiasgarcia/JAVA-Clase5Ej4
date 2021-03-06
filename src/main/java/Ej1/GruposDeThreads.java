package Ej1;
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
public class GruposDeThreads {

    public static void main(String args[]) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("grupo uno");
        ThreadGroup tg2 = new ThreadGroup(tg, "grupo dos");
        MiThreadGrupo t1 = new MiThreadGrupo(tg, "Thread 1.1");
        MiThreadGrupo t12 = new MiThreadGrupo(tg, "Thread 1.2");
        MiThreadGrupo t21 = new MiThreadGrupo(tg2, "Thread 2.1");
        MiThreadGrupo t22 = new MiThreadGrupo(tg2, "Thread 2.2");

        Thread t[] = new Thread[4];
        int i;
        tg.enumerate(t);
        for (i = 0; i < t.length; i++) {
            System.out.println(t[i]);
        }
    }

}
