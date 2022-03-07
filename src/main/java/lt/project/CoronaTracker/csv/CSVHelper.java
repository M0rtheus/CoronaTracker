package lt.project.CoronaTracker.csv;

import lt.project.CoronaTracker.models.CountryCases;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component("csvHelper")
public class CSVHelper {

    private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<CountryCases> allCases = new ArrayList<>();


    public List<CountryCases> fetchData() throws IOException, InterruptedException {
        Iterable<CSVRecord> records = getCsvRecord();
        List<CountryCases> newCases = new ArrayList<>();

        int i = 1;
        for (CSVRecord record : records) {
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            CountryCases countryCase = new CountryCases();
            countryCase.setId(i);
            countryCase.setProvince(record.get("Province/State"));
            countryCase.setCountry(record.get("Country/Region"));
            countryCase.setLatestTotal(latestCases);
            countryCase.setDeltaFromPrevDay(latestCases - prevDayCases);
            newCases.add(countryCase);
            i++;
        }

        this.allCases = newCases;
        return this.allCases;
    }

    public Map<String, String> getCountryCases(String country, String province) throws IOException, InterruptedException {

        Iterable<CSVRecord> records = getCsvRecord();
        Map<String, String> countryCases = new LinkedHashMap<>();

        for (CSVRecord record : records) {
            if (country.equals(record.get("Country/Region")) && province.equals(record.get("Province/State"))) {
                countryCases = record.toMap();
            }

        }

        countryCases.remove("Province/State");
        countryCases.remove("Country/Region");
        countryCases.remove("Lat");
        countryCases.remove("Long");

        return countryCases;
    }

    public Iterable getCsvRecord() throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(csvBodyReader);
        return records;
    }
}
