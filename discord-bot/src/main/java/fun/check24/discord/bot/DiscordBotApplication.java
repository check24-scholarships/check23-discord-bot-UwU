package fun.check24.discord.bot;

import dev.qrowned.config.CommonConfigService;
import dev.qrowned.config.api.ConfigService;
import fun.check24.discord.bot.commands.CommandHandler;
import fun.check24.discord.bot.commands.SlashCommandListener;
import fun.check24.discord.bot.config.BotConfig;
import fun.check24.discord.bot.config.HttpConfig;
import fun.check24.discord.bot.http.HttpRequestHandler;
import fun.check24.discord.bot.logger.BotLogger;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Getter
public class DiscordBotApplication {

    @Getter
    private static DiscordBotApplication instance;

    private final JDA jda;
    private final HttpRequestHandler requestHandler;
    private final CommandHandler commandHandler;
    private final ConfigService configService = new CommonConfigService("./configs/");

    private final BotConfig botConfig;

    public static void main(String[] args) throws InterruptedException {
        new DiscordBotApplication();
    }

    public DiscordBotApplication() throws InterruptedException {
        instance = this;

        this.botConfig = this.configService.registerConfig("bot.json", BotConfig.class);
        BotLogger.getInstance().info(this.botConfig.getIntents().toString());
        JDABuilder jdaBuilder = JDABuilder.create(this.botConfig.getToken(), this.botConfig.getIntents());

        jdaBuilder.setStatus(this.botConfig.getStatus());
        jdaBuilder.setActivity(Activity.playing(this.botConfig.getActivity()));

        this.jda = jdaBuilder.build().awaitReady();

        HttpConfig httpConfig = this.configService.registerConfig("http.json", HttpConfig.class);
        this.requestHandler = new HttpRequestHandler(httpConfig);

        this.commandHandler = new CommandHandler(this.requestHandler);

        jda.addEventListener(new SlashCommandListener(this.commandHandler));
    }

}