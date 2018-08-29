package me.itsshadow.howrecommended.commands;

import java.util.Arrays;

import org.bukkit.entity.Player;

import me.itsshadow.howrecommended.commands.utils.PlayerCommand;
import me.itsshadow.howrecommended.configs.Messages;

public class HowRecommendedCommand extends PlayerCommand{

	public HowRecommendedCommand() {
		super("howrecommended");

		setAliases(Arrays.asList("howr", "hr"));
	}

	@Override
	protected void run(Player player, String[] args) {
		checkArgs(1, Messages.NOT_ENOUGH_ARGS.replace("{prefix}", Messages.PREFIX));

		final String param = args[0].toLowerCase();

		switch (param) {
			case "":
				break;
			default:
				tell(Messages.INAPPROPRIATE_ARGS, false);
		}
	}



}
