package com.plist.service.telegram;

import com.plist.component.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageService {
    private final TelegramBot telegramBot;

    @Autowired
    public SendBotMessageService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    public void sendMessage(Long chatId, String message) {
        SendMessage reply = new SendMessage();
        reply.setChatId(chatId.toString());
        reply.setText(message);
        execute(reply);
    }

    public void sendInlineButtons(Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String... message) {
        SendMessage replyButtons = SendMessage
                .builder()
                .chatId(chatId.toString())
                .replyMarkup(inlineKeyboardMarkup)
                .text("")
                .build();
        if (message.length > 0) replyButtons.setText(message[0]);
        execute(replyButtons);
    }

    private void execute(SendMessage sendMessage) {
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //log
            e.printStackTrace();
        }
    }
}
