import dto.AllSensors;
import dto.Measurements;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sensor {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

//        String response = restTemplate.getForObject(EndPoints.SENSOR_API_URL + EndPoints.ALL_SENSORS, String.class);
//        System.out.println(response);

//        AllSensors sensors = restTemplate.getForObject(EndPoints.SENSOR_API_URL + EndPoints.ALL_SENSORS, AllSensors.class);
//        sensors.getSensor().forEach(System.out::println);



//        ResponseEntity<List<dto.Sensor>> sensorResponse =
//                restTemplate.exchange(EndPoints.SENSOR_API_URL + EndPoints.ALL_SENSORS,
//                        HttpMethod.GET, null, new ParameterizedTypeReference<List<dto.Sensor>>() {
//                        });
//        List<dto.Sensor> sensors = sensorResponse.getBody();
//        sensors.forEach(System.out::println);



        ResponseEntity<List<Measurements>> measurementsResponse =
                restTemplate.exchange(EndPoints.SENSOR_API_URL + EndPoints.ALL_MEASUREMENTS,
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Measurements>>() {
                        });
        List<Measurements> measurements= measurementsResponse.getBody();
        measurements.forEach(System.out::println);


//        Map<String, String> addNewSensorJson = new HashMap<String, String>();
//        addNewSensorJson.put("name", "Sertolovo_Centr");
//
//        HttpEntity<Map<String, String>> addNewSensorRequest = new HttpEntity<Map<String, String>>(addNewSensorJson);
//        String newSensorResponse = restTemplate.postForObject(EndPoints.SENSOR_API_URL + EndPoints.ADD_SENSOR, addNewSensorRequest, String.class);
//        System.out.println(newSensorResponse);
    }
}
