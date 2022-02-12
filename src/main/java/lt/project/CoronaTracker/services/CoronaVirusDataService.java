package lt.project.CoronaTracker.services;

import lt.project.CoronaTracker.models.CountryCases;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<CountryCases> allCases = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 7 * * *")
    public void fetchData() throws IOException, InterruptedException {
        List<CountryCases> newCases = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(csvBodyReader);

        for (CSVRecord record : records) {
            CountryCases countryCase = new CountryCases();
            countryCase.setProvince(record.get("Province/State"));
            countryCase.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            countryCase.setLatestTotal(latestCases);
            countryCase.setDeltaFromPrevDay(latestCases - prevDayCases);
            newCases.add(countryCase);
        }
        this.allCases = newCases;
    }

    public List<CountryCases> getAllCases() {
        return allCases;
    }
}
