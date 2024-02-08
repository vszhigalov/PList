package com.plist.processor;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import com.plist.service.DishService;
import com.plist.service.ProductService;
import com.plist.service.telegram.SendBotMessagePreparator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

@Service
@Transactional
public class GetDishProductListTextMessageProcessor implements TextMessageHandler {
    private final SendBotMessagePreparator sendBotMessagePreparator;

    private final ProductService productService;
    private final DishService dishService;

    @Autowired
    public GetDishProductListTextMessageProcessor(SendBotMessagePreparator sendBotMessagePreparator,
                                                  ProductService productService,
                                                  DishService dishService) {
        this.sendBotMessagePreparator = sendBotMessagePreparator;
        this.productService = productService;
        this.dishService = dishService;
    }

    @Override
    public SendMessage processMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        Dish dish = dishService.getDishByNameAndChatId(text, chatId);
        if (dish == null) {
            return sendBotMessagePreparator.prepareMessage(chatId,
                    "Dish with following name doesn't exist : \n"
                            + text);
        }else {
            return sendBotMessagePreparator.prepareMessage(chatId,
                    dish.getProductList().stream()
                            .map(Product::toString)
                            .collect(Collectors.joining("\n")));
        }
    }
}
