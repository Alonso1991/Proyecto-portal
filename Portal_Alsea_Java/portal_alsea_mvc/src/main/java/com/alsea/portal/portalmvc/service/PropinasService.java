package com.alsea.portal.portalmvc.service;

import com.alsea.portal.portalmvc.dto.PropinasRequestDTO;
import com.alsea.portal.portalmvc.entity.TiendasEntity;
import com.alsea.portal.portalmvc.dto.PropinasResponseDTO;
import com.alsea.portal.portalmvc.repository.ITiendasRepository;
import com.alsea.portal.portalmvc.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PropinasService implements IPropinas {

    @Value("${application.url}")
    String urlOrder;

    final ITiendasRepository tiendasRepository;

    public PropinasService(ITiendasRepository tiendasRepository) {
        this.tiendasRepository = tiendasRepository;
    }

    @Override
    public List<PropinasResponseDTO> getPropinas(String fecIni, String fecFin, String tienda) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        List<PropinasResponseDTO> propinas = new ArrayList<>();
        TiendasEntity tiendaEntity = tiendasRepository.findTiendasEntityById(Integer.parseInt(tienda));
        //Llamamos metodo que invoca api y guardamos en variable
        String jsonPropinas = getPropinasRest(fecIni, fecFin, tiendaEntity.getIp());
        JsonParser jsonParser = new JsonParser(jsonPropinas);
        try {
            JsonObject objectProp = (JsonObject) jsonParser.parse();
            JsonArray arrayDetalle = (JsonArray) objectProp.get("propinas");
            for (int j = 0; j < arrayDetalle.size(); j++) {

                JsonObject unaPropina = (JsonObject) arrayDetalle.get(j);
                PropinasResponseDTO propinasResponseDTO = new PropinasResponseDTO(unaPropina.getString("datePropinas", ""), unaPropina.getInt("sumaPropinas", 0), unaPropina.getInt("cantPropinas", 0));
                propinas.add(propinasResponseDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propinas;
    }

    @Override
    public String getPropinasRest(String fecIni, String fecFin, String ip) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        //Generamos URL
        String url = urlOrder + "?initDate=" + fecIni + "&endDate=" + fecFin + "&ip=" + ip;
        String jsonPropinas = null;
        try {
            System.out.println("url Propinas: " + url);
            HttpUriRequest httppost = null;
            try {
                httppost = RequestBuilder.get()
                        .setUri(new URI(url))
                        .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .setHeader(HttpHeaders.CONNECTION, "keep-alive")
                        .setHeader("X-User-Agent", "X-User-Agent")
                        .build();

            } catch (URISyntaxException e) {
                e.printStackTrace();
                httpclient.close();
                return null;
            }
            CloseableHttpResponse response = httpclient.execute(httppost);
            jsonPropinas = EntityUtils.toString(response.getEntity());
            httpclient.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonPropinas;
    }
}
