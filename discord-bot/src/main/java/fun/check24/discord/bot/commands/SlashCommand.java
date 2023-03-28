package fun.check24.discord.bot.commands;

import fun.check24.discord.bot.DiscordBotApplication;
import fun.check24.discord.bot.config.BotConfig;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public abstract class SlashCommand {

    public final String name;
    public final CommandData commandData;

    public SlashCommand(String name, CommandData commandData) {
        this.name = name;
        this.commandData = commandData;

        DiscordBotApplication.getInstance().getJDA()
                .getGuildById(DiscordBotApplication.getInstance().getBotConfig().getGuildId())
                .upsertCommand(commandData).complete();
        System.out.println("command registered: " + commandData.getName());

    }

    public abstract void handle(SlashCommandInteractionEvent event);

    public abstract String getHelp();

}
