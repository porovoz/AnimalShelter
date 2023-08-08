package com.coffeebreak.animalshelter.listener;
import com.coffeebreak.animalshelter.keyboards.AnimalShelterKeyboard;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

import com.coffeebreak.animalshelter.listener.Constants.*;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final String START_COMMAND = "/start";
    private final TelegramBot telegramBot;
    private final AnimalShelterKeyboard animalShelterKeyboard;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
     private static final Long TELEGRAM_CHAT_VOLUNTEERS = -844733515L;
    private boolean isCat = false;


    public TelegramBotUpdatesListener(TelegramBot telegramBot, AnimalShelterKeyboard animalShelterKeyboard) {
        this.telegramBot = telegramBot;
        this.animalShelterKeyboard = animalShelterKeyboard;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        for (Update update : updates) {
            if (update.message() != null) {
                logger.info("Handles update: {}", update);
                Message message = update.message();
                Long chatId = message.chat().id();
                String messageText = message.text();

                switch (messageText) {

                    case START_COMMAND:
                        animalShelterKeyboard.chooseMenu(chatId);
                        break;

                    case "Приют для кошек":
                        // sendMenuMessage(responses, chatId);
                        isCat = true;
                        animalShelterKeyboard.sendMenuCatShelter(chatId);
//                            sendMessage(chatId, "Вы выбрали приют для кошек");
                        break;

                    case "Приют для собак":
                        isCat = false;
                        animalShelterKeyboard.sendMenuDogShelter(chatId);
//                        sendMessage(chatId, "Вы выбрали приют для собак");
                        break;


                    case "Главное меню":
                        animalShelterKeyboard.chooseMenu(chatId);
                        break;

                    case "Узнать информацию о приюте":
                        animalShelterKeyboard.sendMenuInformationAboutShelter(chatId);
                        break;

                    case "Информация о приюте":
                        if (isCat) {
                            sendMessage(chatId, Constants.INFORMATION_ABOUT_DOG_SHELTER);
                            break;
                        } else {
                            sendMessage(chatId, Constants.INFORMATION_ABOUT_DOG_SHELTER);
                        }
                        break;

                    case "Прислать отчет о питомце":
                        sendMessage(chatId, Constants.INFORMATION_ABOUT_REPORT);
                        sendMessage(chatId, Constants.REPORT_EXAMPLE);
                        break;

                    case "Вернуться в меню":
                        if (isCat) {
                            animalShelterKeyboard.sendMenuCatShelter(chatId);
                            break;
                        } else {
                            animalShelterKeyboard.sendMenuDogShelter(chatId);
                        }
                        break;

                    case "Расписание":
                        if (isCat) {
                            sendMessage(chatId, Constants.SCHEDULE_ABOUT_CAT_SHELTER);
                            break;
                        } else {
                            sendMessage(chatId, Constants.SCHEDULE_ABOUT_DOG_SHELTER);
                            break;
                        }

                    case "Оформление пропуска":
                        if (isCat) {
                            sendMessage(chatId, Constants.REGISTRATION_OF_PASS_ABOUT_CAT_SHELTER);
                            break;
                        } else {
                            sendMessage(chatId, Constants.REGISTRATION_OF_PASS_ABOUT_DOG_SHELTER);
                            break;
                        }

                    case "Техника безопасности":
                        sendMessage(chatId, Constants.TECHNICAL_SAFETY);
                        break;

                    case "Важная информация":
                        if (isCat) {
                            sendMessage(chatId, Constants.INFORMATION_HOW_TO_TAKE_CAT);
                            break;
                        } else {
                            sendMessage(chatId, Constants.INFORMATION_HOW_TO_TAKE_DOG);
                            break;
                        }

                    case "Правила знакомства":
                        if (isCat) {
                            sendMessage(chatId, Constants.RULES_FOR_GETTING_TO_KNOW_A_CAT);
                            break;
                        } else {
                            sendMessage(chatId, Constants.RULES_FOR_GETTING_TO_KNOW_A_DOG);
                            break;
                        }

                    case "Список документов":
                        sendMessage(chatId, Constants.LIST_OF_DOCUMENTS);
                        break;

                    case "Рекомендации к транспортировке":
                        if (isCat) {
                            sendMessage(chatId, Constants.RECOMMENDATIONS_FOR_TRANSPORTATION_CAT);
                            break;
                        } else {
                            sendMessage(chatId, Constants.RECOMMENDATIONS_FOR_TRANSPORTATION_DOG);
                            break;
                        }

                    case "Обустройство жилья котёнка":
                        sendMessage(chatId, Constants.ARRANGEMENT_OF_OF_HOUSING_FOR_A_KITTEN);
                        break;

                    case "Обустройство жилья взрослого кота":
                        sendMessage(chatId, Constants.ARRANGEMENT_OF_OF_HOUSING_FOR_A_ADULT_CAT);
                        break;

                    case "Обустройство жилья кота с инвалидностью":
                        sendMessage(chatId, Constants.ARRANGEMENT_OF_OF_HOUSING_FOR_A_CAT_WITH_A_DISABILITY);
                        break;

                    case "Причины по которым можем не выдать питомца":
                        sendMessage(chatId, Constants.INFORMATION_WE_DO_NOT_GIVE_OUT_PETS);
                        break;

                    case "Позвать волонтёра":
                        sendMessage(chatId, "Я передал ваше сообщение волонтёру, он скоро с вами свяжется. " +
                                  "Если у вас закрытый профиль - поделитесь контактом. " +
                                  "Нажмите справа сверху на  - \"отправить контактные данные\" и волнтёр вам перезвонит");
                        sendForwardMessage(chatId, update.message().messageId());
                        break;

                    case "Как взять кота из приюта":
                        animalShelterKeyboard.sendMenuHowToTakeCat(chatId);
                        break;

                    case "Как взять собаку из приюта":
                        animalShelterKeyboard.sendMenuHowToTakeDog(chatId);
                        break;

                    case "Обустройство жилья щенка":
                        sendMessage(chatId, Constants.ARRANGEMENT_OF_OF_HOUSING_FOR_A_PUPPY);
                        break;

                    case "Обустройство жилья взрослой собаки":
                        sendMessage(chatId, Constants.ARRANGEMENT_OF_OF_HOUSING_FOR_A_DOG);
                        break;

                    case "Обустройство жилья собаки с инвалидностью":
                        sendMessage(chatId, Constants.ARRANGEMENT_OF_OF_HOUSING_FOR_A_DOG_WITH_A_DISABILITY);
                        break;

                    case "Советы кинолога":
                        sendMessage(chatId, Constants.TIPS_FROM_A_DOG_HANDLER);
                        break;

                    case "Контакты проверенных кинологов":
                        sendMessage(chatId, Constants.CONTACTS_OF_THE_DOG_HANDLER);
                        break;

                    case "":
                        System.out.println("Так нельзя");
                        sendMessage(chatId, "Пустое сообщение");
                        break;

                    default:
                        // Обработка незапланированного сценария
                        sendReplyMessage(chatId, "Неизвестная команда", update.message().messageId());
                        break;
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendReplyMessage(Long chatId, String messageText, Integer messageId) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.replyToMessageId(messageId);
        telegramBot.execute(sendMessage);
    }

    public void sendForwardMessage(Long chatId, Integer messageId) {
        ForwardMessage forwardMessage = new ForwardMessage(TELEGRAM_CHAT_VOLUNTEERS, chatId, messageId);
        telegramBot.execute(forwardMessage);
    }

    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            logger.error("Error during sending message: {}", sendResponse.description());
        }
    }
}