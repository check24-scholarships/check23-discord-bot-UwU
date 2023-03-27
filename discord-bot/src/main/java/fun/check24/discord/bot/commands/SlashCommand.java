package fun.check24.discord.bot.commands;

import fun.check24.discord.bot.DiscordBotApplication;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public abstract class SlashCommand {

    public final String name;
    public final CommandData commandData;

    public SlashCommand(String name, CommandData commandData) {
        this.name = name;
        this.commandData = commandData;

        DiscordBotApplication.getInstance().getJDA().upsertCommand(commandData).queue();
    }

    public abstract void handle(SlashCommandInteractionEvent event);

    public abstract String getHelp();

}
