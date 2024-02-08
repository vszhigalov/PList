package com.plist.processor;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TextMessageHandler {
    SendMessage processMessage(Update update);
}
