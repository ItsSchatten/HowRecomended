package me.itsshadow.howrecommended;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.itsshadow.howrecommended.configs.Messages;
import me.itsshadow.howrecommended.configs.Settings;
import me.itsshadow.libs.Utils;

public class HowRecomended extends JavaPlugin {

	@Getter
	private static HowRecomended instance;

	private static PluginDescriptionFile pdf;

	@Override
	public void onEnable() {
		// Set the things.
		instance = this;
		Utils.setInstance(this);
		pdf = this.getDescription();

		// Call the register method, register things. Very good.
		registerThings();
	}

	// We all know what the onEnable and onDisable things do right?

	@Override
	public void onDisable() {
		// Sets things to null to ensure that something isn't going to cause an error.
		instance = null;
		Utils.setInstance(null);

		pdf = null;
	}

	/*
	 * I like to keep everything in a method to find it easily.
	 */
	private void registerThings() {

		final String authors = String.join(", ", pdf.getAuthors());

		// Begin logs message.
		Utils.log(
				"&9+━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━+",
				" ",
				"                          &7How&9Recommended",
				"              &7VERSION > &c" + pdf.getVersion() + " || &7Author > &c" + authors,
				"&7" + pdf.getDescription(),
				" ");

		// Check if vault is present.
		if (!isPluginPresent("Vault")) {
			Utils.log("&cThe plugin, Vault, is needed to use this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}

		// Load configs
		Settings.intit();
		Messages.intit();

		// Set values to allow use in the utils.
		Utils.setPrefix(Messages.PREFIX);
		Utils.setNoPermsMessage(Messages.NO_PERMS);

		// End log message.
		Utils.log("",
				"&9+━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━+",
				" ");
	}

	// Check if a plugin is present.
	private boolean isPluginPresent(String name) {
		return getServer().getPluginManager().getPlugin(name) != null;
	}

}
