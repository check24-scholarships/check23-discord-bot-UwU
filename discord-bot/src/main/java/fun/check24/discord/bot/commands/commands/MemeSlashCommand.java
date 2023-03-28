package fun.check24.discord.bot.commands.commands;

import fun.check24.discord.bot.commands.SlashCommand;
import fun.check24.discord.bot.http.HttpRequestHandler;
import fun.check24.discord.bot.meme.MemeData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public void handle(SlashCommandInteractionEvent event){
        event.deferReply().queue();



        try {
            MemeData meme = this.requestHandler.fetchRandomMeme().get();
            String url = this.requestHandler.getUrlFromMeme(meme);
            EmbedBuilder embed = new EmbedBuilder();
            if (meme.title().isBlank()) {
                embed.setTitle("No title found.");
            }
            else {
                embed.setTitle(meme.title());
            }
            embed.setImage(url);
            embed.setColor(Color.BLUE);
            embed.setFooter(event.getUser().getName() + " | " + event.getTimeCreated().toLocalTime());
            event.getHook().sendMessageEmbeds(embed.build()).queue();
        } catch (URISyntaxException | InterruptedException | ExecutionException e) {
            event.reply("Something went wrong").setEphemeral(true).queue();
        }

    }
}
