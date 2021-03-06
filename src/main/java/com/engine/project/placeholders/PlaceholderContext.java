package com.engine.project.placeholders;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderContext {

    @NotNull
    private final Player player;

    @NotNull
    private String text;

    public PlaceholderContext(@NotNull Player player, @NotNull String text) {
        this.player = player;
        this.text = text;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    public void apply(@NotNull String placeholder, String replace) {
        this.text = StringUtils.replace(text, placeholder, replace);
    }

    @NotNull
    public String getText() {
        return text;
    }
}
