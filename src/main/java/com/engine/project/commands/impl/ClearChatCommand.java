package com.engine.project.commands.impl;

import com.engine.project.commands.Command;
import com.engine.project.configuration.impl.MessageManager;
import me.saiintbrisson.minecraft.command.Execution;
import me.saiintbrisson.minecraft.command.annotations.CommandTarget;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Simple clear chat
 *
 * @author Kewilleen Gomes
 */
public class ClearChatCommand extends Command {

    public ClearChatCommand(MessageManager manager) {
        super(manager);
    }

    @me.saiintbrisson.minecraft.command.annotations.Command(
            name = "clearchat",
            aliases = {"chatclear", "cc", "limparchat"},
            permission = "engine.commands.clearchat",
            target = CommandTarget.BOTH
    )
    public void command(Execution execution) {
        if (execution.argsCount() == 0) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (!player.hasPermission("engine.commands.clearchat.bypass")) {
                    clear(player);
                    player.sendMessage(getManager().getEntry("clearchat.cleaned", execution));
                    return;
                }
                if (execution.isPlayer() && player.equals(execution.getPlayer())) {
                    execution.sendMessage(getManager().getEntry("clearchat.success"));
                    return;
                }
                player.sendMessage(getManager().getEntry("clearchat.try-clear", execution));
            });
        }
        if (execution.argsCount() == 1) {
            String nickname = execution.getArg(0);
            Player player = Bukkit.getPlayer(nickname);
            if (player == null || !player.isOnline()) {
                execution.sendMessage(getManager().getEntry("clearchat.offline"));
                return;
            }
            if (!player.hasPermission("engine.commands.clearchat.bypass")) {
                clear(player);
                player.sendMessage(getManager().getEntry("clearchat.cleaned", execution));
                return;
            }
            player.sendMessage(getManager().getEntry("clearchat.try-clear", execution));
        }
    }
    @me.saiintbrisson.minecraft.command.annotations.Command(
            name = "clearchat.self",
            target = CommandTarget.PLAYER
    )
    public void executeSelf(Execution execution){
        clear(execution.getPlayer());
        execution.sendMessage(getManager().getEntry("clearchat.success"));
    }

    public void clear(Player player) {
        player.sendMessage(StringUtils.repeat("", 100));
    }
}
