import dto.Measurements;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import utils.DataGenerator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Sensor {
    static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) throws InterruptedException {
        dto.Sensor sensor = new dto.Sensor();
        sensor.setName("Pargolovo");
        //addNewSensor(sensor.getName());

        Measurements measurements = new Measurements();
        measurements.setSensor(sensor);

        for (int i = 0; i < 1000; i++) {
            measurements.setValue(DataGenerator.genTemperature());
            measurements.setRaining(DataGenerator.genIsRaining());
            System.out.println(measurements);
            addNewMeasurements(measurements);
            Thread.sleep(100);
        }

/*
        //добавить новый сенсор
        addNewSensor("Pulkovo");

        //добавить 1000 измерений со случайными значениями

        //получить все измерения
        List<Measurements> measurements = getMeasurements();
        measurements.forEach(System.out::println);

        //получить список всех сенсоров
        List<dto.Sensor> sensors = getAllSensors();
        sensors.forEach(System.out::println); */
//        for (int i = 0; i < 3; i++) {
//            System.out.println(String.format("Temperature is: %s, rainy is: %b",DataGenerator.genTemperature(), DataGenerator.genIsRaining()));
//        }


    }
    public static List<Measurements> getMeasurements() {
        ResponseEntity<List<Measurements>> measurementsResponse =
                restTemplate.exchange(EndPoints.SENSOR_API_URL + EndPoints.ALL_MEASUREMENTS,
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Measurements>>() {
                        });
        return measurementsResponse.getBody();
    }
    public static void addNewSensor(String sensorName) {
        Map<String, String> addNewSensorJson = new HashMap<>();
        addNewSensorJson.put("name", sensorName);
        HttpEntity<Map<String, String>> addNewSensorRequest = new HttpEntity<>(addNewSensorJson);
        String newSensorResponse = restTemplate.postForObject(EndPoints.SENSOR_API_URL + EndPoints.ADD_SENSOR, addNewSensorRequest, String.class);
        System.out.println(newSensorResponse);
    }
    public static void addNewMeasurements(Measurements measurements) {
//        Map<String, String> addNewMeasurementsJson = new HashMap<>();
//        addNewMeasurementsJson.put("value", measurements.getValue() + "");
//        addNewMeasurementsJson.put("raining", measurements.isRaining() + "");
//        addNewMeasurementsJson.put("sensor", "{\"name\": \"Pargolovo\"}");
//        System.out.println(addNewMeasurementsJson);
//        HttpEntity<Map<String,String>> addNewMeasurementsRequest = new HttpEntity<>(addNewMeasurementsJson);
//        restTemplate.postForObject(EndPoints.SENSOR_API_URL + EndPoints.ADD_MEASUREMENT, addNewMeasurementsRequest, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Measurements> request = new HttpEntity<>(measurements, headers);
        restTemplate.postForObject(EndPoints.SENSOR_API_URL + EndPoints.ADD_MEASUREMENT, request, String.class);
    }
    public static List<dto.Sensor> getAllSensors() {
        ResponseEntity<List<dto.Sensor>> sensorResponse =
                restTemplate.exchange(EndPoints.SENSOR_API_URL + EndPoints.ALL_SENSORS,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<dto.Sensor>>() {
                        });
        return sensorResponse.getBody();
    }
}
