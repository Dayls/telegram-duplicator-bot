package bot;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import services.ChatIdsLoader;

public class DuplicatorBot extends TelegramLongPollingBot {
	private String sourceChatId;
	private List<String> targetChatIds;
	
	public DuplicatorBot() {
		this.sourceChatId = ChatIdsLoader.getSourceChatId();
		this.targetChatIds = ChatIdsLoader.getDestinationChatIds();
	}
	
	@Override
	public String getBotUsername() {
		return "duplicator_notifier_bot";
	}

	@Override
	public String getBotToken() {
		return "5039139282:AAG7bQRrSidX0ju-l97xZmh5-Odpsrl_zkE";
	}
	
	@Override
	public void onUpdateReceived(Update update) {
			
			
		if(update.hasMessage()
				&& update.getMessage().getChatId().toString().equals(sourceChatId)) {
			
			try {
				for(String destination : targetChatIds) {
					execute(ForwardMessage.builder()
							.fromChatId(update.getMessage().getChatId().toString())
							.chatId(destination)
							.messageId(update.getMessage().getMessageId())
							.build());
				}
				
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}
}
