package fun.check24.discord.bot.config;

import dev.qrowned.config.api.ConfigAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@Getter
@NoArgsConstructor
public final class BotConfig implements ConfigAdapter<BotConfig> {

    private String token;
    private OnlineStatus status;
    private String activity;
    private long guildId;
    private Collection<GatewayIntent> intents;

    @Override
    public void reload(@NotNull BotConfig botConfig) {
        this.token = botConfig.getToken();
        this.status = botConfig.getStatus();
        this.activity = botConfig.getActivity();
        this.intents = botConfig.getIntents();
        this.guildId = botConfig.getGuildId();
    }

}
