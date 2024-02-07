package com.plist.processor;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TextMessageHandler {
    void processMessage(Update update);
}
