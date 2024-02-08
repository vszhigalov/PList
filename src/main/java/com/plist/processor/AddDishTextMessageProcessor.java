package com.plist.processor;

import com.plist.entity.Dish;
import com.plist.service.DishService;
import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AddDishTextMessageProcessor implements TextMessageHandler {

    private final SendBotMessagePreparator sendBotMessageService;
    private final DishService dishService;

    @Autowired
    public AddDishTextMessageProcessor(SendBotMessagePreparator sendBotMessageService,
                                       DishService dishService) {
        this.sendBotMessageService = sendBotMessageService;
        this.dishService = dishService;
    }

    @Override
    public SendMessage processMessage(Update update) { // add fail log there
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        if (dishService.getDishByNameAndChatId(text,chatId) == null){
            dishService.save(new Dish(chatId, text));
            return sendBotMessageService.prepareMessage(chatId,
                    "Dish with following name was added \n" + text);
        } else {
            return sendBotMessageService.prepareMessage(chatId,
                    "Dish name already exist \n" + text);
        }
    }
}
