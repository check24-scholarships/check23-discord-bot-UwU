package fun.check24.discord.bot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class SlashCommandListener extends ListenerAdapter {

    private final CommandHandler commandHandler;

    public SlashCommandListener(@NotNull CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        this.commandHandler.handleSlashCommand(event);
    }

}
