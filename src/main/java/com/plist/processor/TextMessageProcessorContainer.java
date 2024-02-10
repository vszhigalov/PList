package com.plist.processor;

import com.plist.command.RemoveDishCommand;
import com.plist.command.ShowCommonProductsForDishesCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TextMessageProcessorContainer {

    private final Map<String, TextMessageHandler> processorMap;

    @Autowired
    public TextMessageProcessorContainer(AddDishTextMessageProcessor addDishTextMessageProcessor,
                                         RemoveDishMessageProcessor removeDishMessageProcessor,
                                         AddProductToDishTextMessageProcessor addProductToDishTextMessageProcessor,
                                         GetDishProductListTextMessageProcessor getDishProductListTextMessageProcessor,
                                         ShowCommonProductsForDishesTextMessageProcessor showCommonProductsForDishesTextMessageProcessor) {
        this.processorMap = new HashMap<>();
        processorMap.put("/add_dish", addDishTextMessageProcessor);
        processorMap.put("/remove_dish", removeDishMessageProcessor);
        processorMap.put("/add_products_to_dish", addProductToDishTextMessageProcessor);
        processorMap.put("/get_dish_products", getDishProductListTextMessageProcessor);
        processorMap.put("/show_common_products_for_dishes", showCommonProductsForDishesTextMessageProcessor);
    }

    public TextMessageHandler findCommand(String commandName) {
        return processorMap
                .entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow() // add log
                .getValue();
    }
}
