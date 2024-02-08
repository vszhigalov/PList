package com.plist.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CommandContainer {
    private final Map<String, Command> commandMap;

    @Autowired
    public CommandContainer(StartCommand startCommand,
                            AddDishCommand addDishCommand,
                            RemoveDishCommand removeDishCommand) {
        this.commandMap = new HashMap<>();
        commandMap.put("/start", startCommand);
        commandMap.put("/add_dish", addDishCommand);
        commandMap.put("/remove_dish", removeDishCommand);
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
