package com.EcoDelis.dominio;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeorefService {
    private final RestTemplate restTemplate;

    public GeorefService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Provincia> obtenerProvincias() {
        String url = "https://apis.datos.gob.ar/georef/api/provincias";
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<Map<String, Object>> provinciasMap = (List<Map<String, Object>>) response.getBody().get("provincias");

        return provinciasMap.stream()
                .map(map -> {
                    Provincia provincia = new Provincia();
                    provincia.setId(Long.parseLong((String) map.get("id")));
                    provincia.setNombre((String) map.get("nombre"));
                    return provincia;
                })
                .collect(Collectors.toList());
    }

    public List<Localidad> obtenerLocalidadesPorProvincia(String provinciaId) {
        String url = "https://apis.datos.gob.ar/georef/api/localidades?provincia=" + provinciaId;
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<Map<String, Object>> localidadesMap = (List<Map<String, Object>>) response.getBody().get("localidades");

        return localidadesMap.stream()
                .map(map -> {
                    Localidad localidad = new Localidad();
                    localidad.setId(Long.parseLong((String) map.get("id")));
                    localidad.setNombre((String) map.get("nombre"));
                    return localidad;
                })
                .collect(Collectors.toList());
    }
}
