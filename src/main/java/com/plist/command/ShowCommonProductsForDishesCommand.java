package com.plist.command;

import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ShowCommonProductsForDishesCommand implements Command{

    private final SendBotMessagePreparator sendBotMessageService;
    private final String CALLBACK_MESSAGE = "Specify each dish from new line \n" +
            "Like : \n" +
            "Dish 1 \n" +
            "Dish 2 \n" +
            "and etc.";
    @Autowired
    public ShowCommonProductsForDishesCommand(SendBotMessagePreparator sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public SendMessage execute(Update update) {
        return sendBotMessageService.prepareMessage(update
                        .getCallbackQuery()
                        .getMessage()
                        .getChatId()
                , CALLBACK_MESSAGE);
    }
}
