package fun.check24.discord.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class SlashCommandListener extends ListenerAdapter {
    private final CommandHandler commandHandler;
    public SlashCommandListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commandHandler.handleSlashCommand(event);
    }
}
