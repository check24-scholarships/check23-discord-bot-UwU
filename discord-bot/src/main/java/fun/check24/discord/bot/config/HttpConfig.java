package fun.check24.discord.bot.config;

import dev.qrowned.config.api.ConfigAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
public final class HttpConfig implements ConfigAdapter<HttpConfig> {

    private String baseUri;

    @Override
    public void reload(@NotNull HttpConfig httpConfig) {
        this.baseUri = httpConfig.getBaseUri();
    }

}
