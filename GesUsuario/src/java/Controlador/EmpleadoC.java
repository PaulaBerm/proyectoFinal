/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Controlador.Conexion;
import ModeloVO.CUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Paula
 */
public class EmpleadoC extends Conexion {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql;
    CUsuario user = new CUsuario();

    /*   public List listar() {
        ArrayList<infoEmpleado> list = new ArrayList<>();
        String sql = "select * from empleado where id_empleado= 2";
        try {
            ps = getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                emp.setId_empleado(rs.getInt("id_empleado"));
                emp.setNombre_empleado(rs.getString("nombre_empleado"));
                emp.setApellido_empleado(rs.getString("apellido_empleado"));
                list.add(emp);
            }
        } catch (Exception e) {
        }
        return list;
    }*/
    
    public CUsuario list(String correo) {
        String sql = ("SELECT em.*, u.* FROM empleado em INNER JOIN usuario u on u.id_usuario=em.id_usuario WHERE u.correo = '" + correo + "'");
        try {
            ps = getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId_empleado(rs.getInt("id_empleado"));
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre_empleado"));
                user.setApellido(rs.getString("apellido_empleado"));
                user.setCorreo(rs.getString("correo"));
                user.setContraseña(rs.getString("password"));
            }
        } catch (Exception e) {
        }
        return user;
    }

    public static String buscarNombre(String correo) {
        Conexion conexion = new Conexion();
        String busqueda_nombre = null;

        try {
            Connection con = conexion.getConexion();
            String buscar = ("SELECT em.id_empleado,em.nombre_empleado, em.apellido_empleado, u.correo FROM empleado em INNER JOIN usuario u on u.id_usuario=em.id_usuario WHERE u.correo = '" + correo + "'");
            ps = con.prepareStatement(buscar);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre_empleado");
                String apellido = rs.getString("apellido_empleado");
                busqueda_nombre = (nombre + " " + apellido);
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de buscarNombre..." + e.getMessage());
        }

        return busqueda_nombre;
    }
    
    public void modificar(CUsuario use) {
        try {
            String consulta = "update empleado em, usuario u set em.nombre_empleado = ?, em.apellido_empleado = ?, u.correo = ?, u.password = ? where em.id_empleado = ? and u.id_usuario= ?";
            PreparedStatement estatuto = getConexion().prepareStatement(consulta);

            estatuto.setString(1, use.getNombre());
            estatuto.setString(2, use.getApellido());
            estatuto.setString(3, use.getCorreo());
            estatuto.setString(4, use.getContraseña());
            estatuto.setInt(5, use.getId_empleado());
            estatuto.setInt(6, use.getId_usuario());
            estatuto.executeUpdate();
            
        } catch (SQLException e) {

            System.out.println(e);

        }
    }
    
     public void enviarCorreo(String correo) {
        Conexion conexion = new Conexion();
        
        try {
            //Propiedades de conexión
            Properties props = new Properties();
             //host -> de gmail
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            //indica si se va a utilizar gmail
            props.setProperty("mail.smtp.starttls.enable", "true");
            //El puerto de gmail
            props.setProperty("mail.smtp.port", "587");
            //Si se va autentificar con el servidor de gmail
            props.setProperty("mail.smtp.auth", "true");
            
            //Se abre la sesión
            Session session = Session.getDefaultInstance(props);
            //La dirección desde donde se enviarán los correos
            String correoRemitente = "mariaplml190@gmail.com";
            String passwordRemitente = "ANOFREMA123c";
            
            //Correo receptor <- se toma desde la caja de texto
            String correoReceptor = correo;
            String asunto = "Alerta de seguridad";
            String mensaje = "Este mensaje es para notificarle que se ha hecho cambios en su cuenta en <b>INBAR</b>"
                    + "<img src='cid:LogoNombreAbajoInv.png'>";
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));//Quien lo va a enviar
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");
            
            Transport t = session.getTransport("smtp");//Que tipo de transporte será ->smtp
            t.connect(correoRemitente, passwordRemitente);//enviar el correo remitente
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));//se envía el mensaje y a la persona principal
            t.close();

            // JOptionPane.showMessageDialog(null, "Correo Electronico Enviado");         
            
            
        } catch (AddressException ex) {
            Logger.getLogger(EmpleadoC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EmpleadoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
