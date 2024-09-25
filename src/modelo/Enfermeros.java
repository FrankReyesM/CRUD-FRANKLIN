package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmEnfermeros;

/**
 *
 * @author Franklin Reyes
 */
public class Enfermeros {
    
    //Parametros
    private String UUID_Enfermero;
    private String Nombre_Enfermero;
    private int Edad_Enfermero;
    private double Peso_Enfermero;
    private String Correo_Enfermero;
    
    //Getters y Setters
    public String getUUID_Enfermero() {
        return UUID_Enfermero;
    }

    public void setUUID_Enfermero(String UUID_Enfermero) {
        this.UUID_Enfermero = UUID_Enfermero;
    }

    public String getNombre_Enfermero() {
        return Nombre_Enfermero;
    }

    public void setNombre_Enfermero(String Nombre_Enfermero) {
        this.Nombre_Enfermero = Nombre_Enfermero;
    }

    public int getEdad_Enfermero() {
        return Edad_Enfermero;
    }

    public void setEdad_Enfermero(int Edad_Enfermero) {
        this.Edad_Enfermero = Edad_Enfermero;
    }

    public double getPeso_Enfermero() {
        return Peso_Enfermero;
    }

    public void setPeso_Enfermero(double Peso_Enfermero) {
        this.Peso_Enfermero = Peso_Enfermero;
    }

    public String getCorreo_Enfermero() {
        return Correo_Enfermero;
    }


    public void setCorreo_Enfermero(String Correo_Enfermero) {
        this.Correo_Enfermero = Correo_Enfermero;
    }

       public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addEnfermero = conexion.prepareStatement("INSERT INTO tbEnfermeros(UUID_Enfermero, Nombre_Enfermero, Edad_Enfermero, Peso_Enfermero, Correo_Enfermero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addEnfermero.setString(1, UUID.randomUUID().toString());
            addEnfermero.setString(2, getNombre_Enfermero());
            addEnfermero.setInt(3, getEdad_Enfermero());
            addEnfermero.setDouble(4, getPeso_Enfermero());
            addEnfermero.setString(5, getCorreo_Enfermero());
 
            //Ejecutar la consulta
            addEnfermero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
        
    }
       
       public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"Nombre_Enfermero", "Edad_Enfermero", "Peso_Enfermero", "Correo_Enfermero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbEnfermeros");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("Nombre_Enfermero"), 
                    rs.getString("Edad_Enfermero"), 
                    rs.getInt("Peso_Enfermero"), 
                    rs.getString("Correo_Enfermero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    } 
       
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEnfermeros = conexion.prepareStatement("delete from tbEnfermeros where UUID_Enfermero = ?");
            deleteEnfermeros.setString(1, miId);
            deleteEnfermeros.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
        
        public void cargarDatosTabla(frmEnfermeros vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbEnfermeros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbEnfermeros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbEnfermeros.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbEnfermeros.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTb = vista.jtbEnfermeros.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTb = vista.jtbEnfermeros.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTb);
            vista.txtCorreo.setText(CorreoDeTb);
        }
    }
        
            public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbEnfermeros set Nombre_Enfermero= ?, Edad_Enfermero = ?, Peso_Enfermero = ?, Correo_Enfermero = ? where UUID_Enfermero = ?");
                updateUser.setString(1, getNombre_Enfermero());
                updateUser.setInt(2, getEdad_Enfermero());
                updateUser.setDouble(3, getPeso_Enfermero());
                updateUser.setString(4, getCorreo_Enfermero());
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
            
        public void limpiar(frmEnfermeros vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtCorreo.setText("");
        vista.txtPeso.setText("");
    }
      
      
      public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Enfermero", "Nombre_Enfermero", "Edad_Enfermero", "Peso_Enfermero", "Correo_Enfermero"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbEnfermeros WHERE Nombre_Enfermero LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Enfermero"), 
                    rs.getString("Nombre_Enfermero"), 
                    rs.getInt("Edad_Enfermero"), 
                    rs.getDouble("Peso_Enfermero"),
                    rs.getString("Correo_Enfermero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
}
    
