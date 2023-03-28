package fun.check24.discord.bot.commands;

import fun.check24.discord.bot.DiscordBotApplication;
import fun.check24.discord.bot.config.BotConfig;
import fun.check24.discord.bot.logger.BotLogger;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Getter
public abstract class SlashCommand {

    private final String name;
    private final CommandData commandData;

    public SlashCommand(@NotNull String name, @NotNull CommandData commandData) {
        this.name = name;
        this.commandData = commandData;

        Guild guild = DiscordBotApplication.getInstance().getJda()
                .getGuildById(DiscordBotApplication.getInstance().getBotConfig().getGuildId());
        Objects.requireNonNull(guild, "Configured guild cannot be null!")
                .upsertCommand(commandData).complete();
        BotLogger.getInstance().info("Registered slash command with name " + commandData.getName());
    }

    public abstract void handle(SlashCommandInteractionEvent event);

    public abstract String getHelp();

}
