package fun.check24.discord.bot.commands;

import fun.check24.discord.bot.commands.commands.MemeSlashCommand;
import fun.check24.discord.bot.commands.commands.UpdateCommandsSlashCommand;
import fun.check24.discord.bot.http.HttpRequestHandler;
import lombok.Getter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class CommandHandler {

    private final List<SlashCommand> commands = new ArrayList<>();

    public CommandHandler(@NotNull HttpRequestHandler requestHandler) {
        this.commands.add(new MemeSlashCommand(requestHandler));
        //this.commands.add(new UpdateCommandsSlashCommand());
    }

    public void handleSlashCommand(SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        SlashCommand command = getCommand(commandName);

        if (command == null) {
            event.reply("Command not found").setEphemeral(true).queue();
            return;
        }

        command.handle(event);
    }

    private SlashCommand getCommand(String name) {
        return commands.stream().filter(command -> command.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
