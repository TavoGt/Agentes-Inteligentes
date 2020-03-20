package vista;

import algoritmo.Coordenada;
import algoritmo.Dijkstra;
import algoritmo.EstimuloRespuesta;
import algoritmo.Test;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * *
 * posEspeciales[0][0] => Coordenada X - Origen posEspeciales[0][1] =>
 * Coordenada Y - Origen posEspeciales[1][0] => Coordenada X - Meta
 * posEspeciales[1][0] => Coordenada Y - Meta posEspeciales[2][0] => Coordenada
 * X - Origen 2 posEspeciales[2][1] => Coordenada Y - Origen 2
 *
 * @author gtavo
 */
public class Ventana extends JFrame implements ActionListener, KeyListener, Runnable {

    Color colorFondo = Color.BLACK;
    Color colorMeta = Color.BLUE;
    Color colorOrigen = Color.GREEN;
    Color colorOrigen2 = Color.YELLOW;
    Color colorBloque = Color.DARK_GRAY;
    int filas = 0;
    int cols = 0;
    Color colorActual;
    Test test = new Test();
    JRadioButton rBtnMeta = new JRadioButton("Meta");
    JRadioButton rBtnInicio = new JRadioButton("Inicio");
    JRadioButton rBtnBloque = new JRadioButton("Bloque");
    JButton btnMover = new JButton("MOVER");
    JButton btnReiniciar = new JButton("REINICIAR");
    JButton[][] btn;
    int xMeta = 0, yMeta = 0;
    int xOrigen = 0, yOrigen = 0;
    int[][] arr;
    int[][] posEspeciales = new int[3][3];
    boolean meta = false, inicio = false;
    int cntOrg = 0;
    boolean terminado = false;
    List<Coordenada> coordObstaculos = new ArrayList<Coordenada>();
    List<Coordenada> ruta = new ArrayList<>();
    Thread Hilo = new Thread(this); //Objeto para animar las piezas
    int contRutaTmp = 0;
    EstimuloRespuesta moverNoHeuristico;
    boolean empezar = false;

    public Ventana(int filas, int cols) { //Recibe como parametro el título de la ventana
        super("AGENTE INTELIGENTE");

        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.filas = filas;
        this.cols = cols;

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(rBtnMeta);
        bGroup.add(rBtnInicio);
        bGroup.add(rBtnBloque);

        rBtnInicio.setSelected(true);
        //Panel selector
        JPanel panelSelector = new JPanel();
        panelSelector.setLayout(new FlowLayout());
        //panelSelector.setBackground(Color.DARK_GRAY);
        panelSelector.add(rBtnMeta);
        panelSelector.add(rBtnInicio);
        panelSelector.add(rBtnBloque);
        panelSelector.add(btnMover);
        panelSelector.add(btnReiniciar);
        btnMover.addActionListener(this);
        btnMover.addKeyListener(this);
        btnReiniciar.addActionListener(this);

        //Panel de botones (Matriz)
        btn = new JButton[filas][cols];
        arr = new int[filas][cols];

        JPanel panelMatriz = new JPanel();
        GridLayout gl = new GridLayout(filas, cols);
        gl.setHgap(2);
        gl.setVgap(2);
        panelMatriz.setLayout(gl);
        panelMatriz.setBackground(Color.BLACK);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                btn[i][j] = new JButton();
                //btn[i][j].setText("" + (i) + (j));
                arr[i][j] = 0;
                btn[i][j].setBackground(colorFondo);
                btn[i][j].addActionListener(this);
                //btn[i][j].addKeyListener(this);
                panelMatriz.add(btn[i][j]);
            }
        }

        //Agregando objetos al formulario
        Container cp = getContentPane();
        cp.add(panelSelector, BorderLayout.NORTH);
        cp.add(panelMatriz, BorderLayout.CENTER);

        moverNoHeuristico = new EstimuloRespuesta();

        Hilo.start();
    }

    //EVENTOS BOTONES INICIALES
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMover) {
            if (meta && inicio) {
                //repetir();
                //movimiento = new Movimiento(filas, cols, arr, posEspeciales, btn, terminado, coordObstaculos, btnMover);
                empezar = true;
                rutaDijkstra();
            } else {
                if (!inicio) {
                    JOptionPane.showMessageDialog(null, "DEBE ESTABLECER EL INICIO");
                } else if (!meta) {
                    JOptionPane.showMessageDialog(null, "DEBE ESTABLECER UNA META");
                }
            }
        } else if (e.getSource() == btnReiniciar) {
            reiniciar();
            terminado = false;
        } else {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (e.getSource() == btn[i][j]) {
                        if (rBtnMeta.isSelected() && !meta) {
                            meta = true;
                            posEspeciales[1][0] = i;//Fila META
                            posEspeciales[1][1] = j;//Columna META
                            xMeta = i;
                            yMeta = j;
                            arr[i][j] = 0;
                            btn[i][j].setBackground(colorMeta);
                        } else if (rBtnInicio.isSelected() && !inicio && cntOrg < 2) {
                            if (cntOrg == 0) {
                                posEspeciales[0][0] = i; //Fila INICIO
                                posEspeciales[0][1] = j; //Columna INICIO
                                xOrigen = i;
                                yOrigen = j;
                                btn[i][j].setBackground(colorOrigen);
                                cntOrg++;
                            } else {
                                inicio = true;
                                posEspeciales[2][0] = i; //Fila INICIO - NO HEURISTICO
                                posEspeciales[2][1] = j; //Columna INICIO - NO HEURISTICO
                                btn[i][j].setBackground(colorOrigen2);
                                cntOrg++;
                            }
                        } else if (rBtnBloque.isSelected()) {
                            //arr[i][j] = -1;
                            btn[i][j].setBackground(colorBloque);
                            coordObstaculos.add(new Coordenada(i, j));
                            arr[i][j] = 1000;
                        }
                    }
                }
            }
        }
    }

    public void reiniciar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = 0;
                btn[i][j].setBackground(colorFondo);
                btn[i][j].setText("");
                meta = false;
                inicio = false;
                terminado = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (terminado == false) {
            int xTemp = xMeta;
            int yTemp = yMeta;

            if (e.VK_UP == e.getKeyCode()) {
                if ((xMeta > 0) && (terminado == false)) {

                    xMeta--;
                    rutaDijkstra();
                }
            }

            if (e.VK_DOWN == e.getKeyCode()) {
                if ((xMeta + 1 < filas) && (terminado == false)) {
                    xMeta++;
                    rutaDijkstra();
                }
            }

            if (e.VK_RIGHT == e.getKeyCode()) {
                if ((yMeta + 1 < cols) && (terminado == false)) {
                    yMeta++;
                    rutaDijkstra();
                }
            }

            if (e.VK_LEFT == e.getKeyCode()) {
                if ((yMeta > 0) && (terminado == false)) {
                    yMeta--;
                    rutaDijkstra();
                }
            }

            boolean noEsObstaculo = true;
            for (Coordenada coord : coordObstaculos) {
                if (xMeta == coord.getX() && yMeta == coord.getY()) {
                    noEsObstaculo = false;
                    rutaDijkstra();
                }
            }

            if (noEsObstaculo && terminado == false) {
                posEspeciales[1][0] = xMeta;
                posEspeciales[1][1] = yMeta;
                btn[xTemp][yTemp].setBackground(colorFondo);
                btn[xMeta][yMeta].setBackground(colorMeta);
            } else {
                xMeta = xTemp;
                yMeta = yTemp;
            }
        }
        desplazar();
        //desplazarNoHeuristico();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void rutaDijkstra() {
        contRutaTmp = 0;
        Coordenada origen = new Coordenada(posEspeciales[0][0], posEspeciales[0][1]);
        Coordenada meta = new Coordenada(posEspeciales[1][0], posEspeciales[1][1]);
        Test dijkstra = new Test(filas, cols, origen, meta, coordObstaculos);

        ruta = dijkstra.ejecutarDijkstra();
    }

    public void desplazarNoHeuristico() {
        if (terminado == false) {
            int xTemp = posEspeciales[2][0]; //x2
            int yTemp = posEspeciales[2][1]; //y2

            Coordenada coordSigNoH = moverNoHeuristico.sigPosicion(filas, cols, arr, posEspeciales);
            btn[xTemp][yTemp].setBackground(colorFondo);
            btn[coordSigNoH.getX()][coordSigNoH.getY()].setBackground(colorOrigen2);
            if ((coordSigNoH.getX() == posEspeciales[1][0]) && (coordSigNoH.getY() == posEspeciales[1][1])) {
                System.out.println("TERMINADO: AGENTE NO HEURISTICO");
                JOptionPane.showMessageDialog(null, "JUEGO TERMINADO! \nAGENTE NO HEURISTICO");
                empezar = false;
            }
        }

    }

    public void desplazar() {
        if (terminado == false) {
            int r = ruta.size();
            if ((r > 0) && (contRutaTmp <= r)) {
                terminado = false;
                Coordenada coordActual = new Coordenada(posEspeciales[0][0], posEspeciales[0][1]);
                btn[coordActual.getX()][coordActual.getY()].setBackground(colorFondo);
                Coordenada coordNext = ruta.get(contRutaTmp);
                btn[coordNext.getX()][coordNext.getY()].setBackground(colorOrigen);
                posEspeciales[0][0] = coordNext.getX();
                posEspeciales[0][1] = coordNext.getY();
                contRutaTmp++;
            }

            if ((r > 0) && (contRutaTmp == r)) {
                terminado = true;
                Coordenada coordActual = new Coordenada(posEspeciales[0][0], posEspeciales[0][1]);
                btn[coordActual.getX()][coordActual.getY()].setBackground(colorFondo);
                btn[posEspeciales[1][0]][posEspeciales[1][1]].setBackground(colorOrigen);
                System.out.println("Terminado: " + terminado);
                JOptionPane.showMessageDialog(null, "JUEGO TERMINADO! \nAGENTE HEURISTICO");
                empezar = false;
            }
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                //if(terminado == false){
                if (empezar) {
                    desplazar();
                    desplazarNoHeuristico();
                }

                //}
                Thread.sleep(300);
            }
//            while(!terminado){
//                moverNoHeuristico();
//                //System.out.println("HOLA");
//                Thread.sleep(300);
//            }
        } catch (Exception e) {
            System.out.println("Error en la Matrix - xD");
        }
    }
}
