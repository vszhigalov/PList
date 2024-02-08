package com.plist.component;

import com.plist.command.CommandContainer;
import com.plist.configuration.TelegramBotConfig;
import com.plist.processor.TextMessageProcessorContainer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;
    private final CommandContainer commandContainer;
    private final TextMessageProcessorContainer textMessageProcessorContainer;
    private Map<String, String> lastButtonAction = new HashMap<>();


    @Autowired
    public TelegramBot(TelegramBotConfig telegramBotConfig,
                       CommandContainer commandContainer,
                       TextMessageProcessorContainer textMessageProcessorContainer) {
        this.telegramBotConfig = telegramBotConfig;
        this.commandContainer = commandContainer;
        this.textMessageProcessorContainer = textMessageProcessorContainer;
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().startsWith("/")) {
           execute(commandContainer.findCommand(update.getMessage().getText()).execute(update));
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            lastButtonAction.put(callbackQuery.getMessage().getChatId().toString(),
                    callbackQuery.getData());
            execute(commandContainer.findCommand(callbackQuery.getData()).execute(update));
        } else if (update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
           execute(textMessageProcessorContainer
                    .findCommand(lastButtonAction.get(chatId)).processMessage(update));
            lastButtonAction.remove(chatId);
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getBotToken();
    }
}
