package me.itsshadow.howrecommended.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.itsshadow.howrecommended.HowRecommended;
import me.itsshadow.howrecommended.configs.Messages;
import me.itsshadow.howrecommended.configs.Settings;
import me.itsshadow.howrecommended.conversations.RecommendConversation;
import me.itsshadow.libs.Utils;

public class JoinListener implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();

		if (Settings.PLUGIN_ENABLED)
			if (Settings.USE_BYPASS) {
				if (player.hasPermission("howrecommended.admin.bypass")) {
					Utils.tell(player, Messages.BYPASS_MESSAGE.replace("{prefix}", Messages.PREFIX).replace("{player}", player.getName()));
					return;
				}
				new RecommendConversation(player);
			} else

				new BukkitRunnable() {

				@Override
				public void run() {
					Utils.log("Oofers");


				}
			}.runTaskLater(HowRecommended.getInstance(), 8);

	}
}
