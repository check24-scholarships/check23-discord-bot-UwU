package fun.check24.discord.bot.commands.commands;

import fun.check24.discord.bot.DiscordBotApplication;
import fun.check24.discord.bot.commands.CommandHandler;
import fun.check24.discord.bot.commands.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class UpdateCommandsSlashCommand extends SlashCommand {

    public UpdateCommandsSlashCommand() {
        super("updateslashcommands", Commands.slash("updateslashcommands", "Update slash commands")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        deleteCommands(event.getGuild(), true);

    }

    @Override
    public String getHelp() {
        return null;
    }

    public void deleteCommands(Guild guild, boolean delete) {
        if (delete) {
            guild.retrieveCommands().queue(commands -> {

                for (Command cmd : commands) {
                    cmd.delete().queue();
                }

            });
        }

    }

    public void updateCommands(Guild guild) {
        List<CommandData> commandDataList = new ArrayList<>();

        for (SlashCommand cmd : DiscordBotApplication.getInstance().getCommandHandler().getCommands()) {
            commandDataList.add(cmd.commandData);
            System.out.println(cmd.name);
        }
        guild.updateCommands().addCommands(commandDataList).submit();
    }
}
