package com.plist.button;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
public enum StartButton {

    ADD_DISH(
            InlineKeyboardButton.builder()
            .text("Add dish")
            .callbackData("/add_dish")
            .build()),
    GET_DISH_PRODUCT_LIST(
            InlineKeyboardButton.builder()
            .text("Get dish products list")
            .callbackData("/get_dish_products")
            .build()),
    REMOVE_DISH(
            InlineKeyboardButton.builder()
            .text("Remove dish")
            .callbackData("/remove_dish")
            .build()),
    ADD_PRODUCTS_TO_DISH(
            InlineKeyboardButton.builder()
                    .text("Add products to dish")
                    .callbackData("/add_products_to_dish")
                    .build()),
    SHOW_COMMON_PRODUCTS_FOR_DISHES(
            InlineKeyboardButton.builder()
            .text("Show common products for dishes")
            .callbackData("/show_common_products_for_dishes")
            .build());

    private InlineKeyboardButton inlineKeyboardButton;

    StartButton(InlineKeyboardButton inlineKeyboardButton) {
        this.inlineKeyboardButton = inlineKeyboardButton;
    }
}
