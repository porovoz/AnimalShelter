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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger ( TelegramBotUpdatesListener.class );

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener ( this );
    }

    @Override
    public int process(List<Update> updates) {

        for (Update update : updates) {
            if (update.message () != null) {
                logger.info ( "Handles update: {}" , update );
                Message message = update.message ();
                Long chatId = message.chat ().id ();
                String messageText = message.text ();

                switch (messageText) {
                    case "/start":
                        sendMessage ( chatId , "Добро пожаловать! Я бот приюта для животных. Выберите приют: Приют для кошек или Приют для собак." );
                        sendMenuKeyboard ( chatId );
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

    private void sendMessage(Long chatId , String message) {
        SendMessage sendMessage = new SendMessage ( chatId , message );
        SendResponse sendResponse = telegramBot.execute ( sendMessage );
        if (!sendResponse.isOk ()) {
            logger.error ( "Error during sending message: {}" , sendResponse.description () );
        }
    }

    private void sendMenuKeyboard(Long chatId) {
        InlineKeyboardButton buttonDogShelter = new InlineKeyboardButton ( "Приют для собак" ).callbackData ( "dog_shelter" );
        InlineKeyboardButton buttonCatShelter = new InlineKeyboardButton ( "Приют для кошек" ).callbackData ( "cat_shelter" );
        InlineKeyboardButton buttonGetInformationAboutShelter = new InlineKeyboardButton ( "Узнать информацию о приюте" ).callbackData ( "info_shelter" );
        InlineKeyboardButton buttonTakeAnimal = new InlineKeyboardButton ( "Как взять животное из приюта" ).callbackData ( "take_animal" );
        InlineKeyboardButton buttonPetReport = new InlineKeyboardButton ( "Прислать отчет о питомце" ).callbackData ( "pet_report" );
        InlineKeyboardButton buttonCallVolunteer = new InlineKeyboardButton ( "Позвать волонтера" ).callbackData ( "call_volunteer" );
        InlineKeyboardButton[][] buttons =
                {
                        {buttonCatShelter , buttonDogShelter} ,
                        {buttonGetInformationAboutShelter} ,
                        {buttonTakeAnimal} ,
                        {buttonPetReport , buttonCallVolunteer}
                };
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup ( buttons );
        SendMessage sendMessage = new SendMessage ( chatId , "Выберите нужный пункт меню:" )
                .replyMarkup ( inlineKeyboardMarkup );

        SendResponse sendResponse = telegramBot.execute ( sendMessage );
        if (!sendResponse.isOk ()) {
            logger.error ( "Error during sending message: {}" , sendResponse.description () );
        }
    }


}