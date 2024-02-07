package com.plist.processor;

import com.plist.entity.Dish;
import com.plist.service.DishService;
import com.plist.service.telegram.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AddDishTextMessageProcessor implements TextMessageHandler {

    private final SendBotMessageService sendBotMessageService;
    private DishService dishService;

    @Autowired
    public AddDishTextMessageProcessor(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void processMessage(Update update) { // add fail log there
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        dishService.save(new Dish(chatId, text));
        sendBotMessageService.sendMessage(chatId,
                "Dish with following name was added \n" + text);
    }
}
