package cl.alsea.portal.propinas.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utils {
    public static void escribirLog( String mensaje) {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        InputStream input = Utils.class.getClassLoader().getResourceAsStream("aplication.properties");
        Properties prop = new Properties();

        try {

            prop.load(input);
            fh = new FileHandler(prop.getProperty("RUTA_LOGS"),true);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info(mensaje);
            fh.close();

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
