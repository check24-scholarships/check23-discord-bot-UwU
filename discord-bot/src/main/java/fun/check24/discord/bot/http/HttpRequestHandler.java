package fun.check24.discord.bot.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fun.check24.discord.bot.config.HttpConfig;
import fun.check24.discord.bot.meme.MemeData;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public final class HttpRequestHandler {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final HttpConfig httpConfig;

    private CompletableFuture<MemeData> fetchRandomMeme() throws URISyntaxException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(this.httpConfig.getBaseUri() + "/meme/"))
                .GET()
                .build();
        return this.httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(stringHttpResponse -> GSON.fromJson(stringHttpResponse.body(), MemeData.class));
    }

    private String getUrlFromMeme(@NotNull MemeData memeData) {
        return this.httpConfig.getBaseUri() + "/meme/" + memeData.uuid() + ".jpg";
    }

    public CompletableFuture<String> fetchRandomMemeUrl() throws URISyntaxException {
        return this.fetchRandomMeme().thenApplyAsync(this::getUrlFromMeme);
    }

}
