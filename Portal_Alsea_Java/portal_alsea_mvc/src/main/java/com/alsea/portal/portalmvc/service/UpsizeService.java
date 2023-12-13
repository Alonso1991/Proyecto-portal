package com.alsea.portal.portalmvc.service;
/*
import com.alsea.portal.portalmvc.dto.UpsizeResponseDTO;
import com.alsea.portal.portalmvc.entity.CajerosEntity;
import com.alsea.portal.portalmvc.entity.TiendasUpsizeEntity;
import com.alsea.portal.portalmvc.repository.ITiendasUpsizeRepository;
import com.alsea.portal.portalmvc.util.JsonArray;
import com.alsea.portal.portalmvc.util.JsonObject;
import com.alsea.portal.portalmvc.util.JsonParser;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UpsizeService implements IUpsize{


    @Value("${application.url}")
    String urlOrder;

    final ITiendasUpsizeRepository tiendasUpsizeRepository;
    private List<UpsizeResponseDTO> upsizes;

    public UpsizeService(ITiendasUpsizeRepository tiendasUpsizeRepository) {
        this.tiendasUpsizeRepository = tiendasUpsizeRepository;

    }


    @Override
    public List<UpsizeResponseDTO> getUpsize(String fecIni, String fecFin, String tienda){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        List<UpsizeResponseDTO> Upsize = new ArrayList<>();
        TiendasUpsizeEntity tiendasUpsize = tiendasUpsizeRepository.findTiendasUpsizeEntityById(Integer.parseInt(tienda));

        //Llamamos metodo que invoca api y guardamos en variable

        String jsonUpsizes = getUpsizeRest(fecIni, fecFin, TiendasUpsizeEntity.getIp());

        JsonParser jsonParser = new JsonParser(jsonUpsizes);
        try {
            JsonObject objectProp = (JsonObject) jsonParser.parse();
            JsonArray arrayDetalle = (JsonArray) objectProp.get("upsize");
            for (int j = 0; j < arrayDetalle.size(); j++) {

                JsonObject unUpsize = (JsonObject) arrayDetalle.get(j);
                UpsizeResponseDTO upsizeResponseDTO = new UpsizeResponseDTO(unUpsize.getString("dateUpsize", ""));

                upsizes.add(upsizeResponseDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return upsizes;
    }

    @Override
    public String getUpsizeRest(String fecIni, String fecFin, String ip) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        //Generamos URL
        String url = urlOrder + "?initDate=" + fecIni + "&endDate=" + fecFin + "&ip=" + ip;
        String jsonUpsize = null;
        try {
            System.out.println("url Upsize: " + url);
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
            jsonUpsize = EntityUtils.toString(response.getEntity());
            httpclient.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonUpsize;
    }



}
*/