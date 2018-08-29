package me.itsshadow.howrecommended.commands.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.itsshadow.howrecommended.configs.Messages;
import me.itsshadow.libs.Utils;

public abstract class PlayerCommand extends Command{


	private Player player;
	private String[] args;

	@Getter @Setter(value=AccessLevel.PRIVATE)
	private String commandLabel;

	@Setter(value=AccessLevel.PROTECTED)
	private String prefix = Utils.getPrefix();

	public PlayerCommand(String name) {
		super(name);
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player))
			returnTell("&cOnly players can use this command.", true);

		final Player player = (Player)sender;
		this.player = player;

		setCommandLabel(commandLabel);
		this.args = args;

		try {
			run(player, args);
		} catch (final ReturnedCommandExeception ex) {
			final String tellMessage = ex.tellMessage;

			tell(tellMessage, ex.usePrefix);
		}

		return true;
	}

	protected abstract void run(Player player, String[] args);

	protected int getNumber(int argsIndex, int from, int to, String errorMessage) {
		int number = 0;

		try {
			number = Integer.parseInt(args[argsIndex]);

			Validate.isTrue(number >= from && number <= to);

		} catch (final IllegalArgumentException ex) {
			returnTell(errorMessage.replace("{min}", from + "").replace("{max}", to + ""), true);
		}

		return number;

	}

	protected void checkNotNull(Object toCheck, String nullMessage) {
		if (toCheck == null)
			returnTell(nullMessage, true);
	}

	protected void checkArgs(int minLength, String message) {
		if (args.length < minLength)
			returnTell(message, false);
	}

	protected void checkArgsStrict(int requiredAmount, String message) {
		if (args.length != requiredAmount)
			returnTell(message, true);
	}

	protected void returnTell(String message, boolean usePrefix) {
		throw new ReturnedCommandExeception(message, usePrefix);
	}

	protected void tell(String message, boolean usePrefix) {
		Utils.tell(player, message, usePrefix);
	}

	protected void checkPerms(Player player, String permission) {
		if (!player.hasPermission(permission))
			returnTell(Messages.NO_PERMS.replace("{prefix}", Messages.PREFIX).replace("{player}", player.getName()).replace("{permission}", permission), false);
	}

	@RequiredArgsConstructor
	private final class ReturnedCommandExeception extends RuntimeException {
		private static final long serialVersionUID = 1L;

		private final String tellMessage;
		private final boolean usePrefix;

	}

}
