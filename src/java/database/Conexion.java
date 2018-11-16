/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
 * @author Yahir
 */

public class Conexion{

    public static synchronized Connection getConexion(){
        
        //variables a utilizar
        Connection cn=null;
        String url = null, user = null, password = null, parameters = null, host= null, database = null;
        host = "localhost";
        user = "root";
        password = "root1234";
        database = "wadProyect";
        parameters = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
        

        //Creamos url desde el archivo de propiedades
        url = "jdbc:mysql://" + host + "/" + database + parameters;
        
        
        try{
            //Creamos la conexión a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection(url,user,password);
            System.out.println("Conectado a base");
        } catch (Exception e){
            System.out.println("Error al conectar a la base " + e.toString());
            System.out.println(e);
            //Imprimimos error en caso de que haya
            e.printStackTrace();
        } finally {
            //regresamos la conexión
            return cn;
        }
    }
    //Métodos para cerrar conexión y sus elementos
    public static synchronized void closeStatement(CallableStatement cs){
        try{
           cs.close();
        } catch (Exception e){
           e.printStackTrace();
        }
    }
    public static synchronized void closeResultset(ResultSet rs){
        try{
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static synchronized void closeConexion(Connection cn){
        try{
            cn.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //Método para deshacer cambios en la base de datos en caso de error.
    public static synchronized void rollback(Connection cn){
        try{
            cn.rollback();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
   
}