package com.plist.processor;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import com.plist.service.DishService;
import com.plist.service.ProductService;
import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AddProductToDishTextMessageProcessor implements TextMessageHandler{

    private final SendBotMessagePreparator sendBotMessagePreparator;

    private final ProductService productService;
    private final DishService dishService;

    @Autowired
    public AddProductToDishTextMessageProcessor(SendBotMessagePreparator sendBotMessagePreparator
            , ProductService productService, DishService dishService) {
        this.sendBotMessagePreparator = sendBotMessagePreparator;
        this.productService = productService;
        this.dishService = dishService;
    }


    @Override
    public SendMessage processMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        List<String> preparedList = new ArrayList<>(
                Arrays.asList(update.getMessage().getText().split("\n")));
        Dish dish = dishService.getDishByNameAndChatId(preparedList.get(0), chatId);
        if (dish == null){
            return sendBotMessagePreparator.prepareMessage(chatId,
                    "Dish with following name doesn't exist : \n"
                            + preparedList.get(0));
        }else {
            preparedList.remove(0);
         preparedList.forEach(x -> productService.save(new Product(x, 0.0, 0.0, dish.getId())));
            return sendBotMessagePreparator.prepareMessage(chatId,
                    "Products for following dish were added : \n"
                            + dish.getName());
        }
    }
}
