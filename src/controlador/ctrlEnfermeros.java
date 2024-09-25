
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Enfermeros;
import vista.frmEnfermeros;

/**
 *
 * @author Franklin Reyes
 */
public class ctrlEnfermeros implements MouseListener, KeyListener{
    
        //1- Mandar a llamar a las otras capas (modelo y vista)
    private Enfermeros modelo;
    private frmEnfermeros vista;
    
    public ctrlEnfermeros(Enfermeros modelo, frmEnfermeros vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnIngresar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbEnfermeros);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbEnfermeros.addMouseListener(this);
        vista.btnEditar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnIngresar){
            modelo.setNombre_Enfermero(vista.txtNombre.getText());
            modelo.setEdad_Enfermero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Enfermero(Double.parseDouble(vista.txtEdad.getText()));
            modelo.setCorreo_Enfermero(vista.txtEdad.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbEnfermeros);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbEnfermeros);
            modelo.Mostrar(vista.jtbEnfermeros);
        }
        
        if(e.getSource() == vista.jtbEnfermeros){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnEditar){
            modelo.setNombre_Enfermero(vista.txtNombre.getText());
            modelo.setEdad_Enfermero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Enfermero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Enfermero(vista.txtCorreo.getText());
            modelo.Actualizar(vista.jtbEnfermeros);
            modelo.Mostrar(vista.jtbEnfermeros);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
                if(e.getSource() == vista.txtBuscar){
            modelo.Buscar(vista.jtbEnfermeros, vista.txtBuscar);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
