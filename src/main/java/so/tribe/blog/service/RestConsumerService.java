package so.tribe.blog.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class RestConsumerService {

    private RestTemplate restTemplate;

    public RestConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get An Object by a Get Request To an Api
     * @param url
     * @param pathVariables Path Variables Of the Url
     * @param params Params of our Url
     * @throws so.tribe.blog.exception.ConsumedApiNotFoundException
     * @return Object
     */
    public Object getSimpleObjectMethod(String url, HashMap<String,Object> pathVariables , HashMap<String,Object> params){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        // Adding Path Variables To Uri
        builder.uriVariables(pathVariables);
        // Adding Params To Url
        params.forEach(builder::queryParam);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(builder.toUriString(),Object.class);
        return responseEntity.getBody();
    }
}
