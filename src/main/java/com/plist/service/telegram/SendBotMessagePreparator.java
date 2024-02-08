package com.plist.service.telegram;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public class SendBotMessagePreparator {

    public SendMessage prepareMessage(Long chatId, String message) {
        SendMessage reply = new SendMessage();
        reply.setChatId(chatId.toString());
        reply.setText(message);
        return reply;
    }

    public SendMessage prepareInlineButtons(Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String... message) {
        SendMessage replyButtons = SendMessage
                .builder()
                .chatId(chatId.toString())
                .replyMarkup(inlineKeyboardMarkup)
                .text("")
                .build();
        if (message.length > 0) replyButtons.setText(message[0]);
        return replyButtons;
    }
}
