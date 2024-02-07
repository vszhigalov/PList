package com.plist.command;

import com.plist.service.telegram.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AddDishCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final String CALLBACK_MESSAGE = "Specify dish name";

    @Autowired
    public AddDishCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update
                        .getCallbackQuery()
                        .getMessage()
                        .getChatId()
             , CALLBACK_MESSAGE);
    }
}
