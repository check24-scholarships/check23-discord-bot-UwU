package fun.check24.discord.bot;

import dev.qrowned.config.CommonConfigService;
import dev.qrowned.config.api.ConfigService;
import fun.check24.discord.bot.config.BotConfig;
import fun.check24.discord.bot.config.HttpConfig;
import fun.check24.discord.bot.http.HttpRequestHandler;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Getter
public class DiscordBotApplication {

    @Getter
    private static DiscordBotApplication instance;

    private final JDA jda;
    private final ConfigService configService = new CommonConfigService("./configs/");

    private final HttpRequestHandler requestHandler;

    public static void main(String[] args) {
        new DiscordBotApplication();
    }

    public DiscordBotApplication() {
        instance = this;

        BotConfig botConfig = this.configService.registerConfig("bot.json", BotConfig.class);
        System.out.println(botConfig.getIntents());
        JDABuilder jdaBuilder = JDABuilder.create(botConfig.getToken(), botConfig.getIntents());

        jdaBuilder.setStatus(botConfig.getStatus());
        jdaBuilder.setActivity(Activity.playing(botConfig.getActivity()));

        HttpConfig httpConfig = this.configService.registerConfig("http.json", HttpConfig.class);
        this.requestHandler = new HttpRequestHandler(httpConfig);

        this.jda = jdaBuilder.build();
    }

}