package fun.check24.discord.bot.meme;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record MemeData(@NotNull String id,
                       @NotNull UUID uuid,
                       @NotNull String title) {
}
