package cl.alsea.portal.propinas.dao;

import cl.alsea.portal.propinas.dto.PropinasResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;


@Repository
public class PropinasDAO {


    @Value("${spring.datasource.hikari.jdbc-url}")
    private String urlTemp;
    @Value("${spring.datasource.hikari.username}")
    private String userName;
    @Value("${spring.datasource.hikari.password}")
    private String pass;

    public List<PropinasResponseDTO> getPropinas(Date iniDate, Date endDate,String ip) throws SQLException {
        List<PropinasResponseDTO> response = new ArrayList<>();
        //Se declara archivo de propiedades
        Connection conn = null;
        try {
            String url = insert(urlTemp,ip,16);
            System.out.println("URL CONNECTION BD: "+ url);
            //Forma en que me funciono la conexion a Sybase
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver").newInstance();
            //Restamos uno a la fecha porque si no query no toma el día inicial
            String fecIni = formatDateToString(iniDate);

            //String dburl = "jdbc:sqlanywhere:uid=dba;pwd=micros;eng=micros;database=micros;links=tcpip(host=172.31.25.10)";
            System.out.println("Conectando: " + url + ";" + userName + ";" + pass);
            // System.out.println("Conectando: " + dburl);
            //Creando conexion
            conn = DriverManager.getConnection(url,userName,pass);
            ResultSet rs;
            Statement statement = conn.createStatement();
            //Consulta a BD
            String query ="SELECT MicrosBsnzDate AS FECHA, SUM(TipAmount)AS SUMA,count(TipAmount)AS CANT FROM custom.CC_TRX  WHERE FECHA >='"+fecIni+"' AND FECHA <='"+endDate+"' and TipAmount>0 Group by FECHA Order by FECHA Desc";
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

    public String formatDateToString(Date date){

        String pattern = "yyyy-MM-dd 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }
}
