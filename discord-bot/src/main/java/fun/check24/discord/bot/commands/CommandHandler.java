package fun.check24.discord.bot.commands;

import fun.check24.discord.bot.commands.commands.MemeSlashCommand;
import fun.check24.discord.bot.commands.commands.UpdateCommandsSlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    private final List<SlashCommand> commands = new ArrayList<>();

    public CommandHandler() {
        this.commands.add(new MemeSlashCommand());
        this.commands.add(new UpdateCommandsSlashCommand());
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

        return commands.stream().filter(command -> command.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<SlashCommand> getCommands() {
        return commands;
    }
}
