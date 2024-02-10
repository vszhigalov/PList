package com.plist.processor;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import com.plist.service.DishService;
import com.plist.service.ProductService;
import com.plist.service.telegram.SendBotMessagePreparator;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Service
public class ShowCommonProductsForDishesTextMessageProcessor implements TextMessageHandler {

    private final SendBotMessagePreparator sendBotMessagePreparator;
    private final ProductService productService;
    private final DishService dishService;

    public ShowCommonProductsForDishesTextMessageProcessor(SendBotMessagePreparator sendBotMessagePreparator,
                                                           ProductService productService,
                                                           DishService dishService) {
        this.sendBotMessagePreparator = sendBotMessagePreparator;
        this.productService = productService;
        this.dishService = dishService;
    }

    @Override
    public SendMessage processMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        List<String> dishNameList = Arrays.stream(
                        update.getMessage()
                                .getText()
                                .split("\n"))
                                .toList();
        List<Dish> dishList = new LinkedList<>();
        for (String dishName : dishNameList){
            Dish dish = dishService.getDishByNameAndChatId(dishName.trim(), chatId);
            if (dish == null){
                return sendBotMessagePreparator.prepareMessage(chatId, "Dish with "+dishName+" doesn't exist \n" +
                        "please recheck your dish list and try again");
            }else {
                dishList.add(dish);
            }
        }
        List<Product> products = productService.getCommonProductsForDishes(dishList);
        return   sendBotMessagePreparator.prepareMessage(chatId, "Buy list for dishes: \n" +
                dishList.stream().map(Dish::getName).collect(joining(", ")) + " \nCommon products:\n" +
                products.stream().map(Product::toString).collect(joining("\n")));

    }
}
