package com.plist.command;

import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class GetDishProductListCommand implements Command{

    private final SendBotMessagePreparator sendBotMessagePreparator;

    private final String CALLBACK_MESSAGE = "Specify dish name";
    @Autowired
    public GetDishProductListCommand(SendBotMessagePreparator sendBotMessagePreparator) {
        this.sendBotMessagePreparator = sendBotMessagePreparator;
    }

    @Override
    public SendMessage execute(Update update) {
        return sendBotMessagePreparator.prepareMessage(update
                        .getCallbackQuery()
                        .getMessage()
                        .getChatId()
                , CALLBACK_MESSAGE);
    }
}
