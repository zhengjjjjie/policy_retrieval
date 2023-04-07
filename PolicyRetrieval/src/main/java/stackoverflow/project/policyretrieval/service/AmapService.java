package stackoverflow.project.policyretrieval.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author 18263
 * @version 1.0
 * Create by 2023/4/7 17:34
 */
@Service
public class AmapService {
    private static final String URL = "https://restapi.amap.com/v3/ip?key=9b0c423eb5a9e63a4eba9881553e911f&ip=";

    public String getAddressByIp(String ip) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + ip, String.class);
        String result = responseEntity.getBody();
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject.getString("province") + jsonObject.getString("city");
    }
}
