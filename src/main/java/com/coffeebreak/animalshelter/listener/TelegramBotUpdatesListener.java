package com.coffeebreak.animalshelter.listener;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger (TelegramBotUpdatesListener.class);

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        for (Update update : updates) {
            if (update.message() != null) {
                logger.info ("Handles update: {}", update);
                Message message = update.message();
                Long chatId = message.chat().id();
                String messageText = message.text();

                    switch(messageText) {
                        case "/start":
                            sendMessage(chatId , "Добро пожаловать! Я бот приюта для животных. Выберите приют: Приют для кошек или Приют для собак.");
                            sendInlineKeyboard(chatId);
                            break;
                        case "Приют для кошек":
                            // sendMenuMessage(responses, chatId);
                            break;
                        case "Приют для собак":
                            // sendMenuMessage(responses, chatId);
                            break;
                        case "Узнать информацию о приюте (этап 1)":
                            // sendInfoMessage(responses, chatId);
                            break;
                        case "Как взять животное из приюта (этап 2)":
                            // sendTakePetMessage(responses, chatId);
                            break;
                        case "Прислать отчет о питомце (этап 3)":
                            // sendReportMessage(responses, chatId);
                            break;
                        case "Позвать добровольца":
                            // sendVolunteerMessage(responses, chatId);
                            break;
                        default:
                            // Обработка незапланированного сценария
                            break;
                    }

            }

        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }

    private void sendInlineKeyboard(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonDogShelter = new InlineKeyboardButton("Приют для собак");
        InlineKeyboardButton buttonCatShelter = new InlineKeyboardButton("Приют для кошек");
        buttonDogShelter.callbackData("dog_shelter");
        buttonCatShelter.callbackData("cat_shelter");
        inlineKeyboardMarkup.addRow(buttonDogShelter, buttonCatShelter);

        SendMessage sendMessage = new SendMessage(chatId, "Выберите приют:")
                .replyMarkup(inlineKeyboardMarkup);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}