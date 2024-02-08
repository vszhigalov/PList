package com.plist.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RemoveDishCommand implements Command {
    @Override
    public SendMessage execute(Update update) {
        return null;  //TODO: tbd
    }
}
