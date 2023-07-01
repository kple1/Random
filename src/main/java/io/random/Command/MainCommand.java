package io.random.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

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
                        Build build = new Build();
                        build.onCommand(sender, command, label, args);
                    }

                    case "출석보상" -> {
                        CheckReward checkReward = new CheckReward();
                        checkReward.onCommand(sender, command, label, args);
                    }

                    case "등록" -> {
                        Registration registration = new Registration();
                        registration.onCommand(sender, command, label, args);
                    }

                    case "공지설정" -> {
                        NoticeSet noticeSet = new NoticeSet();
                        noticeSet.onCommand(sender, command, label, args);
                    }
                }
            }
        }
        return false;
    }
}
