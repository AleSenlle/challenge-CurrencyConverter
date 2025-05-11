package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ExchangeRateAPI {
    private static final String API_KEY = "cb6e924b9413d8b772018e07";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String url = BASE_URL + fromCurrency + "/" + toCurrency;
        // Creamos un httpclient
        HttpClient client = HttpClient.newHttpClient();
        // Ahora configuramos la httprequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Y ahora enviamos la solicitud y obtenemos la respuesta con httpresponse
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Por ultimo, la procesamos
        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);
            return apiResponse.conversion_rate;
        } else {
            throw new Exception("Error: " + response.body());
        }
    }

    // Clase interna para convertir el json que devuelve la api en un objeto
    private static class ApiResponse {
        String base_code;
        String target_code;
        double conversion_rate;
    }
}
