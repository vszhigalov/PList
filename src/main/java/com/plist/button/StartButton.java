package com.plist.button;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
public enum StartButton {

    ADD_DISH_BUTTON(
            InlineKeyboardButton.builder()
            .text("Add dish")
            .callbackData("/add_dish")
            .build()),
    GET_DISH_PRODUCT_LIST(
            InlineKeyboardButton.builder()
            .text("Get dish products list")
            .callbackData("/get_dish_products")
            .build()),
    REMOVE_DISH_BUTTON(
            InlineKeyboardButton.builder()
            .text("Remove dish")
            .callbackData("/remove_dish")
            .build()),
    ADD_PRODUCTS_TO_DISH(
            InlineKeyboardButton.builder()
                    .text("Add products to dish")
                    .callbackData("/add_products_to_dish")
                    .build());
//    ADD_DISH_TO_BUY_LIST(
//            InlineKeyboardButton.builder()
//            .text("Add dish to buy list")
//            .callbackData("add_dish_to_buy_list")
//            .build());

    private InlineKeyboardButton inlineKeyboardButton;

    StartButton(InlineKeyboardButton inlineKeyboardButton) {
        this.inlineKeyboardButton = inlineKeyboardButton;
    }
}
