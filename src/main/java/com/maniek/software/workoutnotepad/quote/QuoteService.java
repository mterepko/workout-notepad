package com.maniek.software.workoutnotepad.quote;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;

    private final RestTemplate restTemplate;

    private final Gson gson;

    private static final String ZEN_QUOTES_API_URL = "https://zenquotes.io/api/today";

    public Quote getAndSaveQuote() {

        Quote quote = quoteRepository.findByCreationDate(LocalDate.now());

        if(quote == null){

            String response = restTemplate.getForObject(ZEN_QUOTES_API_URL, String.class);

            if (response != null) {
                JsonArray jsonArray = gson.fromJson(response, JsonArray.class);
                if (jsonArray.size() > 0) {
                    JsonObject quoteObject = jsonArray.get(0).getAsJsonObject();
                    String message = quoteObject.get("q").getAsString();
                    String author = quoteObject.get("a").getAsString();

                    Quote newQuote = new Quote(message, author);
                    quoteRepository.save(newQuote);

                    return newQuote;
                }
            }
            throw new RuntimeException("Failed to fetch quote from Zen Quotes API");
        } else {
            return quote;
        }
    }
}
