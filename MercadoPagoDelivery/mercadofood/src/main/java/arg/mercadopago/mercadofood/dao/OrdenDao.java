package arg.mercadopago.mercadofood.dao;

import arg.mercadopago.mercadofood.entity.OrderValidateEntity;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdenDao {

    private static final Logger log = LoggerFactory.getLogger(OrdenDao.class);
    private final String SQL_SELECT_CANCELA= "SELECT order_agregador FROM ORDENES WHERE ingreso_date>= getDate()-0.2 and estado ='CANCELARMP'";
    private String SQL_SELECT_VALIDA= "SELECT order_agregador FROM ORDENES WHERE ingreso_date>= getDate()-3 and order_agregador='";

    @Autowired
    Environment env;

    public OrdenDao(){

    }

    private  String urlStatusOrder;

    @Transactional
    public void registrarOrden(OrderValidateEntity ordenEntity) {
        String mensaje="";
        String mensaje2="";


        try {
            SimpleJdbcCall simpleCall = new SimpleJdbcCall(customDataSource())
                    .withSchemaName("dbo")
                    .withoutProcedureColumnMetaDataAccess()
                    .withProcedureName("insertOrdenes")
                    .declareParameters(
                            new SqlParameter("marca", Types.VARCHAR),
                            new SqlParameter("estado", Types.VARCHAR),
                            new SqlParameter("date_ingreso", Types.DATE),
                            new SqlParameter("date_modifY", Types.DATE),
                            new SqlParameter("order_agregador", Types.VARCHAR),
                            new SqlParameter("store_id", Types.VARCHAR),
                            new SqlParameter("mensaje", Types.LONGNVARCHAR));

            // TODO agregar parametro donacion

            Map<String, Object> inParams = new HashMap<>();

            inParams.put("marca", ordenEntity.getMarca());
            inParams.put("estado", ordenEntity.getStatus());
            inParams.put("date_ingreso", ordenEntity.getDate());
            inParams.put("date_modifY", ordenEntity.getDate());
            inParams.put("order_agregador", ordenEntity.getOrderAgregador());
            inParams.put("store_id", ordenEntity.getStore_id());
            inParams.put("mensaje", ordenEntity.getOrderRequest());
            simpleCall.execute(inParams);
            log.info("ORDEN: "+ ordenEntity.getOrderAgregador()+ " PROCESADA EXITOSAMENTE");
        }catch (DataIntegrityViolationException e){
            log.error("MENSAJE: ERROR AL INTENTAR INGRESAR ORDEN, ORDEN YA EXISTE PROCEDURE:" + ordenEntity.getIdOrder());


        }catch (NullPointerException e){
            log.error("MENSAJE: ERROR AL INTENTAR INGRESAR ORDEN PROCEDURE:" + ordenEntity.getIdOrder() + " EXCEPTION:" + Throwables.getStackTraceAsString(e));

        }
        catch (Exception e){
            log.error("MENSAJE: ERROR AL INTENTAR INGRESAR ORDEN PROCEDURE:" + ordenEntity.getIdOrder() + " EXCEPTION:" + Throwables.getStackTraceAsString(e));

        }

    }

    public DataSource customDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         dataSource.setUrl("jdbc:sqlserver://192.168.245.2:1433;databaseName=MercadoFoods");
        dataSource.setUsername("ghernandez");
        dataSource.setPassword("Alsea.001");

        return dataSource;

    }
public void updateStatusOrden(String idOrdenAgregador, String status) {
    try {
        SimpleJdbcCall simpleCall = new SimpleJdbcCall(customDataSource())
                .withSchemaName("dbo")
                .withoutProcedureColumnMetaDataAccess()
                .withProcedureName("actualizaOrdenes")
                .declareParameters(
                        new SqlParameter("estado", Types.VARCHAR),
                        new SqlParameter("order_agregador", Types.VARCHAR));

        // TODO agregar parametro donacion

        Map<String, Object> inParams = new HashMap<>();

        inParams.put("estado", status);
        inParams.put("order_agregador", idOrdenAgregador);
        simpleCall.execute(inParams);
        log.info("ORDEN: "+ idOrdenAgregador+ " ACTUALIZADA EXITOSAMENTE");

    }catch (NullPointerException e){
        log.error("MENSAJE: ERROR AL INTENTAR ACTUALIZAR ORDEN PROCEDURE:" + idOrdenAgregador + " EXCEPTION:" + Throwables.getStackTraceAsString(e).substring(0,50));
    }
    catch (Exception e){
        log.error("MENSAJE: ERROR AL INTENTAR ACTUALIZAR ORDEN PROCEDURE:" + idOrdenAgregador + " EXCEPTION:" + Throwables.getStackTraceAsString(e).substring(0,100));

    }
    }

    public List<String> recuperaOrdenesCanceladas(){
        List<String> ordersAgregator=new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Creating a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.245.2:1433;databaseName=MercadoFoods", "ghernandez", "Alsea.001");
            Statement st = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_CANCELA);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String orderAgregador =rs.getString("order_agregador");
                ordersAgregator.add(orderAgregador);
                 //updateStatusOrden(idAgregador, "CANCELADAMP");

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ordersAgregator;
    }


    public Boolean validaOrdenIngresada(String idOrder){
        boolean retorna = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Creating a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.245.2:1433;databaseName=MercadoFoods", "ghernandez", "Alsea.001");
            SQL_SELECT_VALIDA= SQL_SELECT_VALIDA+idOrder+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_VALIDA);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                retorna = true;

            }
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
            retorna = false;
            return retorna;
        }
        return retorna;
    }


}
