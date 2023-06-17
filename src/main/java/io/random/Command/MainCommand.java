package io.random.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {

            if (args.length == 0) {
                //TODO
            }

            if (args.length >= 1) {
                switch (args[0]) {
                    case "제작" -> {
                        CreateRandom createRandom = new CreateRandom();
                        createRandom.onCommand(sender, command, label, args);
                    }

                    case "삭제" -> {
                        RemoveRandom removeRandom = new RemoveRandom();
                        removeRandom.onCommand(sender, command, label, args);
                    }

                    case "목록" -> {
                        ListRandom listRandom = new ListRandom();
                        listRandom.onCommand(sender, command, label, args);
                    }

                    case "당첨아이템" -> {
                        WinnerItemRandom winnerItemRandom = new WinnerItemRandom();
                        winnerItemRandom.onCommand(sender, command, label, args);
                    }

                    case "설정" -> {
                        //TODO
                    }

                    case "출첵제작" -> {
                        //TODO
                    }

                    case "출첵삭제" -> {
                        //TODO
                    }

                    case "출첵목록" -> {
                        //TODO
                    }

                    case "출첵당첨아이템" -> {
                        //TODO
                    }

                    case "출첵설정" -> {
                        //TODO
                    }
                }
            }
        }
        return false;
    }
}