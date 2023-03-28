package fun.check24.discord.bot.commands.commands;

import fun.check24.discord.bot.commands.SlashCommand;
import fun.check24.discord.bot.http.HttpRequestHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.net.URISyntaxException;

public final class MemeSlashCommand extends SlashCommand {

    private final HttpRequestHandler requestHandler;

    public MemeSlashCommand(@NotNull HttpRequestHandler requestHandler) {
        super("meme",
                Commands.slash("meme", "Send a random meme")
                        .addSubcommands(
                                new SubcommandData("delete", "Delete a meme"),
                                new SubcommandData("get", "Get a meme"))
        );
        this.requestHandler = requestHandler;
    }

    @Override
    public String getHelp() {
        return "Send a random meme --- written by Github Copilot";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        try {
            this.requestHandler.fetchRandomMemeUrl().thenAccept(url -> {
                event.getHook().sendMessage(url).queue();
            });
        } catch (URISyntaxException e) {
            event.reply("Something went wrong").setEphemeral(true).queue();
        }

    }
}
