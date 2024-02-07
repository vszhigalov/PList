package com.plist.command;

import com.plist.button.StartButton;
import com.plist.service.telegram.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final String START_MESSAGE = "Main menu";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = Arrays.stream(StartButton.values())
                .map(StartButton::getInlineKeyboardButton)
                .toList();
        inlineKeyboardMarkup.setKeyboard(List.of(buttons));

        sendBotMessageService.sendInlineButtons(update.getMessage().getChatId(),
                inlineKeyboardMarkup,
                START_MESSAGE);
    }
}
