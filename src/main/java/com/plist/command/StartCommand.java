package com.plist.command;

import com.plist.button.StartButton;
import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;
@Component
public class StartCommand implements Command {
    private final SendBotMessagePreparator sendBotMessageService;
    private final String START_MESSAGE = "Main menu";
    @Autowired
    public StartCommand(SendBotMessagePreparator sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public SendMessage execute(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = Arrays.stream(StartButton.values())
                .map(StartButton::getInlineKeyboardButton)
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(buttons));

        return sendBotMessageService.prepareInlineButtons(update.getMessage().getChatId(),
                inlineKeyboardMarkup,
                START_MESSAGE);
    }
}
