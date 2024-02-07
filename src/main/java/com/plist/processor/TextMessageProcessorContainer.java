package com.plist.processor;

import com.plist.service.telegram.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

public class TextMessageProcessorContainer {

    private final Map<String, TextMessageHandler> processorMap;


    public TextMessageProcessorContainer(SendBotMessageService sendBotMessageService) {
        this.processorMap = new HashMap<>();
        processorMap.put("/add_dish", new AddDishTextMessageProcessor(sendBotMessageService));
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
