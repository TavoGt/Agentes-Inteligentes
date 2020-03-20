package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaInicial extends JFrame{
    
    JTextField txtFilas = new JTextField(3);
    JTextField txtColumnas = new JTextField(3);
    JButton btnAceptar = new JButton("ACEPTAR");
    int filas, cols;
    
    public VentanaInicial(){
        super("AGENTE INTELIGENTE");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panel datos
        JPanel panelDatos = new JPanel();
        GridLayout gl = new GridLayout(2,2);
        panelDatos.add(new JLabel("Filas: "));
        panelDatos.add(txtFilas);
        panelDatos.add(new JLabel("Columnas: "));
        panelDatos.add(txtColumnas);
        
        //Panel de botón
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout());
        panelBoton.add(btnAceptar);
        
        Container cp = getContentPane();
        cp.add(panelDatos, BorderLayout.CENTER);
        cp.add(panelBoton, BorderLayout.SOUTH);
        
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filas = Integer.parseInt(txtFilas.getText());
                cols = Integer.parseInt(txtColumnas.getText());
                VentanaInicial v = new VentanaInicial();
                v.dispose();
                Ventana ventana = new Ventana(filas, cols);
                ventana.show();
                
            }
        });
    }
    
}
