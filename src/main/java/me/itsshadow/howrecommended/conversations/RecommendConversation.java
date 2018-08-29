package me.itsshadow.howrecommended.conversations;

import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

import me.itsshadow.howrecommended.HowRecommended;
import me.itsshadow.howrecommended.configs.Messages;
import me.itsshadow.howrecommended.configs.Settings;
import me.itsshadow.libs.Utils;

public class RecommendConversation {

	public RecommendConversation(Player player) {
		final Conversation convo = new ConversationFactory(HowRecommended.getInstance())
				.withModality(Settings.DONT_RECEIVECHATS)
				.withPrefix(context -> Utils.colorize(Messages.CONVO_PREFIX))
				.withEscapeSequence(Settings.EXIT_PHRASE)
				.thatExcludesNonPlayersWithMessage("Oof you shouldn't be able to access this....")
				.addConversationAbandonedListener(e -> {
					final Conversable c = e.getContext().getForWhom();

					if (e.gracefulExit()) {
						Utils.log("Test");
						c.sendRawMessage(Utils.colorize(Messages.SUCCESS_MESSAGE));
					}
					else
						c.sendRawMessage("456...");

				})
				.withFirstPrompt(new NamePrompt())
				.buildConversation(player);

		player.beginConversation(convo);
	}

	enum Data {
		PLAYER_NAME
	}

	class NamePrompt extends StringPrompt {

		@Override
		public Prompt acceptInput(ConversationContext context, String input) {

			context.setSessionData(Data.PLAYER_NAME, input);

			return Prompt.END_OF_CONVERSATION;
		}

		@Override
		public String getPromptText(ConversationContext context) {
			return Utils.colorize(Messages.PROMPT_MESSAGE);
		}

	}

}
