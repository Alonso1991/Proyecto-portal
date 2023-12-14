package cl.alsea.portal.propinas.dao;

import cl.alsea.portal.propinas.dto.PropinasResponseDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PropinasDAO {



    private String url = "jdbc:mysql://localhost:3306/propinas";

    private String userName = "root";

    private String pass = "user";

    public List<PropinasResponseDTO> getPropinas(Date initDate, Date endDate) throws SQLException {
        List<PropinasResponseDTO> response = new ArrayList<>();
        //Se declara archivo de propiedades
        Connection conn = null;
        try {
            String iniDatestr = formatDate(initDate);
            String endDatestr = formatDate(endDate);
            System.out.println("URL CONNECTION BD: "+ url);
            //Forma en que me funciono la conexion a Mysql
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //Restamos uno a la fecha porque si no query no toma el día inicial


            //String dburl = "jdbc:sqlanywhere:uid=root;pwd=user;eng=mysql;database=propinas;links=tcpip(host=127.0.0.1)";
            System.out.println("Conectando: " + url + ";" + userName + ";" + pass);
            // System.out.println("Conectando: " + dburl);
            //Creando conexion
            conn = DriverManager.getConnection(url,userName,pass);
            ResultSet rs;
            Statement statement = conn.createStatement();
            //Consulta a BD
            String query ="SELECT fecha_propina AS FECHA, SUM(cantidad_propinas) AS SUMA, COUNT(cantidad_propinas) AS CANT FROM propinas WHERE fecha_propina >= '"+iniDatestr+"' AND fecha_propina <='"+endDatestr+"' AND cantidad_propinas > 0 Group by FECHA Order by FECHA Desc";
            System.out.println();
            //Consulta obtiene datos
            rs = statement.executeQuery(query);
            System.out.println("Query ejecutada");


            while (rs.next()) {
                System.out.println("Datos cargados: " + rs.getDate("FECHA") + "," + rs.getInt("SUMA") + "," + rs.getInt("CANT"));
                response.add(new PropinasResponseDTO(rs.getInt("SUMA"), rs.getDate("FECHA"), rs.getInt("CANT")));
            }

            System.out.println("Lista cargada");


        } catch (SQLException e) {
            System.err.println("Error en la conexion a BD" + Arrays.toString(e.getStackTrace()));
            conn.close();
            System.out.println("Error en la conexion a BD" + Arrays.toString(e.getStackTrace()));
            System.err.println(e.getMessage());


        } catch (UnsupportedOperationException e) {
            System.err.println("Error en la inyeccion de la lista" + Arrays.toString(e.getStackTrace()));

            System.out.println("Error en la inyeccion de la lista" + Arrays.toString(e.getStackTrace()));
            conn.close();

        } catch (Exception e) {
            System.err.println("Error en la ejecucion de PropinasImpl " + Arrays.toString(e.getStackTrace()));
            System.out.println("Error en la ejecucion de PropinasImpl" + Arrays.toString(e.getStackTrace()));
            conn.close();


        }
        conn.close();
        return response;
    }
    public static String insert(String bag, String marble, int index) {
        String bagBegin = bag.substring(0,index);
        String bagEnd = bag.substring(index);
        return bagBegin + marble + bagEnd;
    }

    public String formatDate(Date date){

        String pattern = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String strDate= simpleDateFormat.format(date);
        return strDate;
    }
}
