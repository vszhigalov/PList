package com.plist.processor;

import com.plist.entity.Dish;
import com.plist.service.DishService;
import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class RemoveDishMessageProcessor implements TextMessageHandler {

    private final SendBotMessagePreparator sendBotMessagePreparator;

    private final DishService dishService;

    @Autowired
    public RemoveDishMessageProcessor(SendBotMessagePreparator sendBotMessagePreparator,
                                      DishService dishService) {
        this.sendBotMessagePreparator = sendBotMessagePreparator;
        this.dishService = dishService;
    }

    @Override
    public SendMessage processMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        Dish dish = dishService.getDishByNameAndChatId(text,chatId);
        dishService.delete(dish);
        return sendBotMessagePreparator.prepareMessage(chatId,
                "Dish with following name was removed \n" + text);
    }
}
