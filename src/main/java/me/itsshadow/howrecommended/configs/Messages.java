package me.itsshadow.howrecommended.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.itsshadow.howrecommended.configs.utils.SimpleConfig;
import me.itsshadow.libs.Utils;

public class Messages extends SimpleConfig {

	@Getter @Setter(value=AccessLevel.PRIVATE)
	private static Messages instance;

	public Messages(String fileName) {
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
		new Messages("messages.yml").onLoad();
	}

	public static String PREFIX, NO_PERMS, BYPASS_MESSAGE, SUCCESS_MESSAGE, PROMPT_MESSAGE, CONVO_PREFIX, NOT_ENOUGH_ARGS, INAPPROPRIATE_ARGS;

	public void onLoad() {
		PREFIX = getString("prefix");
		NO_PERMS = getString("no-perms");
		BYPASS_MESSAGE = getString("bypass-message");
		SUCCESS_MESSAGE = getString("success-message");
		PROMPT_MESSAGE = getString("prompt-text");
		CONVO_PREFIX = getString("convo-prefix");
		NOT_ENOUGH_ARGS = getString("not-enough-args");
		INAPPROPRIATE_ARGS = getString("inappropriate-args");
	}

	public void reloadMessages() {
		setInstance(null);

		new Messages("messages.yml").onLoad();

		Utils.setPrefix(Messages.PREFIX);
		Utils.setNoPermsMessage(NO_PERMS);

		setInstance(this);
		Utils.log("File, messages.yml, has been reloaded!");
	}

}
