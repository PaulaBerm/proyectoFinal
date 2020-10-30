/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Sara Rodriguez
 */
public class ProductoDAO {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    
    public Producto listarID(int id){
    
            String sql="select * from producto where id_producto="+id;
            Producto p=new Producto();
            try {
           
                con=cn.getConexion();
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();
                while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setPrecio(rs.getDouble(4));
                p.setDescripcion(rs.getString(5));
                p.setStock(rs.getInt(6));
           
                    
                }
            
        } catch (Exception e) {
        }
            return p;
    }
    
    public List listarCategoria(int categoria){
        List<Producto>producto=new ArrayList();
        String sql="select * from producto where id_categoria="+categoria;
        try{
            con=cn.getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setPrecio(rs.getDouble(4));
                p.setDescripcion(rs.getString(5));
                p.setStock(rs.getInt(6));
                producto.add(p);
                
                
            }
            
        } catch (Exception e){
        }
        return producto;
    }
    public List listar(){
        List<Producto>producto=new ArrayList();
        String sql="select * from producto ";
        try{
            con=cn.getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setPrecio(rs.getDouble(4));
                p.setDescripcion(rs.getString(5));
                p.setStock(rs.getInt(6));
                producto.add(p);
                
                
            }
            
        } catch (Exception e){
        }
        return producto;
    }
    public void listarImg(int id, HttpServletResponse response){
        String sql="select * from producto where id_producto="+id;
        InputStream inputStream=null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutStream = null;
        try {
            outputStream=response.getOutputStream();
            con=cn.getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            if(rs.next()){
                inputStream=rs.getBinaryStream("foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutStream = new BufferedOutputStream(outputStream);
            int i=0;
            while ((i=bufferedInputStream.read())!=-1) {
                bufferedOutStream.write(i);
                
            }
            
        }catch (Exception e){
            
        }
        
        
    }
    
    
}
