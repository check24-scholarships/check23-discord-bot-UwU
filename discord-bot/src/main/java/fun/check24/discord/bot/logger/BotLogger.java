package fun.check24.discord.bot.logger;

import fun.check24.discord.bot.DiscordBotApplication;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BotLogger {

    @Getter
    private static BotLogger instance = new BotLogger(LoggerFactory.getLogger(DiscordBotApplication.class));

    private final Logger logger;

    private BotLogger(@NotNull Logger logger) {
        this.logger = logger;
    }

    public void info(String s) {
        this.logger.info(s);
    }

    public void warn(String s) {
        this.logger.warn(s);
    }

    public void warn(String s, Throwable t) {
        this.logger.warn(s, t);
    }

    public void severe(String s) {
        this.logger.error(s);
    }

    public void severe(String s, Throwable t) {
        this.logger.error(s, t);
    }

    public void debug(String s) {
        this.logger.debug(s);
    }

}