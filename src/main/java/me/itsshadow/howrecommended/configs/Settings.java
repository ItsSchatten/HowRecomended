package me.itsshadow.howrecommended.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.itsshadow.howrecommended.configs.utils.SimpleConfig;
import me.itsshadow.libs.Utils;

public class Settings extends SimpleConfig {
	@Getter @Setter(value=AccessLevel.PRIVATE)
	private static Settings instance;

	public Settings(String fileName) {
		super(fileName);

		setHeader(new String[]{
				"--------------------------------------------------------",
				" This configuration file has been automatically updated!",
				"",
				" Unfortunately, due to the way Bukkit saves .yml files,",
				" all comments in your file where lost. please open",
				" " + fileName + " directly to browse the default values.",
				"--------------------------------------------------------"

		});

		setInstance(this);
	}

	public static void intit() {
		new Settings("settings.yml").onLoad();
	}

	public static boolean USE_BYPASS;

	public void onLoad() {
		USE_BYPASS = (boolean) get("use-bypass");
	}

	public void reloadMessages() {
		setInstance(null);

		new Settings("settings.yml").onLoad();


		setInstance(this);
		Utils.log("File, settings.yml, has been reloaded!");
	}

}
