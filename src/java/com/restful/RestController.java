/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restful;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
@Path("RestController")
public class RestController {

    @GET
    @Path("/getData")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UsuarioModel> GetDataInJSON() throws ClassNotFoundException, SQLException {
        ArrayList<UsuarioModel> usuarios = new ArrayList<>();
        Connection con = null;
        String Usuario = "root";
        String Contraseña = "Danielmi";
        String query = "select * from restful.datosusuario ";
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false", Usuario, Contraseña);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            UsuarioModel um = new UsuarioModel();
            um.setId(rs.getInt("id"));
            um.setEmail(rs.getString("Usuario"));
            um.setPasswordHash(rs.getString("Contraseña"));
            usuarios.add(um);
        }

        return usuarios;
    }

    @PUT
    @Path("Login")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean autentication(String UsuarioALogear) throws ClassNotFoundException, SQLException {
        boolean login = false;
        Connection con = null;
        Gson Usergson = new Gson();
        //String User="djcuello@uninorte.edu.co";
        //String Password="123456";
        System.out.println(UsuarioALogear);
        UsuarioModel u = Usergson.fromJson(UsuarioALogear, UsuarioModel.class);
        String User = u.getEmail();
        String Password = u.getPasswordHash();

        String Usuario = "root";
        String Contraseña = "Danielmi";
        String query = "select Usuario,Contraseña from restful.datosusuario ";
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false", Usuario, Contraseña);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            if (rs.getString("Usuario").equals(User) && rs.getString("Contraseña").equals(Password)) {
                login = true;
            }
        }

        return login;
    }

    @PUT
    @Path("Register")
    @Produces(MediaType.APPLICATION_JSON)
    public String Register(String JsonUser) throws ClassNotFoundException, SQLException, SQLException, JSONException {
        // String email="pruebajp@gmail.com";
        //  String password="123456";
        // UsuarioModel User = (UsuarioModel) UsuarioARegistrar.
        String result="false";
        //SONObject result = new JSONObject();
        System.out.println(JsonUser);
        Gson UserG = new Gson();
        UsuarioModel UsuarioRegistrar = UserG.fromJson(JsonUser, UsuarioModel.class);
        String User = UsuarioRegistrar.getEmail();
        String password = UsuarioRegistrar.getPasswordHash();
        
                
        String Usuario = "root";
        String Contraseña = "Danielmi";
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false", Usuario, Contraseña);
        int x = 0;
        try {

            PreparedStatement ps = con.prepareStatement("insert into restful.datosusuario(id, usuario, contraseña) values(?,?,?)");
            ps.setInt(1, 1043);
            ps.setString(2, User);
            ps.setString(3, password);
            x = ps.executeUpdate();
            if (x == 1) {
                result="true";
            } else {
                result="false";
            }
            ps.close();

        } catch (SQLException e) {
        }

        return result;
    }

    
      
    @PUT
    @Path("IngresoHistorico")
    @Produces(MediaType.APPLICATION_JSON)
    public String IngresoHistorico(String JsonUser) throws ClassNotFoundException, SQLException, SQLException, JSONException {
        
        String result="true";
        System.out.println(JsonUser);
        Gson UserG = new Gson();
        HistoricoModel UsuarioHist = UserG.fromJson(JsonUser, HistoricoModel.class);
        
        String User = UsuarioHist.getEmail();
        float latitude = UsuarioHist.getLatitude();
        float longitude = UsuarioHist.getLongitude();
        String FechaH = UsuarioHist.getFechaHora();
        
                
        String Usuario = "root";
        String Contraseña = "Danielmi";
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false", Usuario, Contraseña);
        
        try {

            PreparedStatement ps = con.prepareStatement("insert into restful.historico(idHisotrico, Usuario, latitude,longitude,Fecha) values(?,?,?,?,?)");
            
            ps.setString(2, User);
            ps.setFloat(3,latitude);
            ps.setFloat(4, longitude);
            ps.setString(5, FechaH);
            ps.close();

        } catch (SQLException e) {
        result="false";
        }

        return result;
    }
    
    
    
    @GET
    @Path("Historico")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HistoricoModel> Historico(String JsonUser) throws ClassNotFoundException, SQLException {
        ArrayList<HistoricoModel> Historico = new ArrayList<>();

        Connection con = null;
        //String User = "djcuello@uninorte.edu.co";
         System.out.println(JsonUser);
        Gson UserG = new Gson();
        HistoricoModel UsuarioRegistrar = UserG.fromJson(JsonUser, HistoricoModel.class);
        String User = UsuarioRegistrar.getEmail();
        
        
        
        String Usuario = "root";
        String Contraseña = "Danielmi";
        String query = "select * from restful.historico ";
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false", Usuario, Contraseña);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        HistoricoModel user = new HistoricoModel();

        while (rs.next()) {
            HistoricoModel hm = new HistoricoModel();
            if (rs.getString("Usuario").equals(User)) {
                hm.setIdHisotrico(rs.getInt("IdHisotrico"));
                hm.setEmail(rs.getString("Usuario"));
                hm.setLatitude(rs.getFloat("Latitude"));
                hm.setLongitude(rs.getFloat("Longitude"));
                hm.setFechaHora(rs.getString("Fecha"));
                Historico.add(hm);
            }

        }

        return Historico;
    }

}

