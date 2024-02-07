package com.plist.command;

import com.plist.service.telegram.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CommandContainer {
    private final Map<String, Command> commandMap;

    @Autowired
    public CommandContainer(SendBotMessageService sendBotMessageService) {
        this.commandMap = new HashMap<>();
        commandMap.put("/start", new StartCommand(sendBotMessageService));
        commandMap.put("/add_dish", new AddDishCommand(sendBotMessageService));
    }

    public Command findCommand(String commandName){
        return commandMap
                .entrySet()
                .stream()
                .filter(x -> x.getKey().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow() // add log
                .getValue();
    }
}
