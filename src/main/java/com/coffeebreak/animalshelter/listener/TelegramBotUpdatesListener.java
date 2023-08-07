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

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final String START_COMMAND = "/start";
    private static final String GREETING_TEXT = "Добро пожаловать! Я бот приюта для животных. Выберите приют: Приют для кошек или Приют для собак.";

    private static final String INFORMATION_ABOUT_BOT = """
            Информация о возможностях бота:\s
            - Показывает информацию о приюте\s
            - Показывает какие документы нужны\s
            - Принимает ежедневный отчет о питомце\s
            - Передает контактные данные добровольцам для связи""";

    private static final String INFORMATION_ABOUT_CAT_SHELTER = """
            Сайт с информацией о приюте для кошек\s
            https://google.com\s
            Контактные данные\s
            https://yandex.ru\s
            Общие рекомендации\s
            https://ru.wikipedia.org\s
            """;

    private static final String INFORMATION_ABOUT_DOG_SHELTER = """
            Сайт с информацией о приюте для собак\s
            https://google.com\s
            Контактные данные\s
            https://yandex.ru\s
            Общие рекомендации\s
            https://ru.wikipedia.org\s
            """;

    private static final String INFORMATION_ABOUT_CATS = """
            Правила знакомства с животным\s
            https://google.com\s
            Список документов\s
            https://yandex.ru
            Список рекомендаций\s
            https://ru.wikipedia.org
            Прочая информация\s
            https://google.com
            """;

    private static final String INFORMATION_ABOUT_DOGS = """
            Правила знакомства с животным\s
            https://google.com\s
            Список документов\s
            https://yandex.ru
            Список рекомендаций\s
            https://ru.wikipedia.org
            Советы кинолога\s
            https://ru.wikipedia.org
            Прочая информация\s
            https://google.com
            """;

    private static final String INFORMATION_ABOUT_REPORT = """
            Для отчета нужна следующая информация:\s
            - Фото животного  \s
            - Рацион животного\s
            - Общее самочувствие и привыкание к новому месту\s
            - Изменение в поведении: отказ от старых привычек, приобретение новых.\s
            Скопируйте следующий пример и не забудьте прикрепить фото""";

    private static final String REPORT_EXAMPLE = """
            Рацион: ваш текст;\s
            Самочувствие: ваш текст;\s
            Поведение: ваш текст;""";

    private static final String REGEX_MESSAGE = """
            (Рацион:)(\\s)(\\W+)(;)\s
            (Самочувствие:)(\\s)(\\W+)(;)
            (Поведение:)(\\s)(\\W+)(;)""";

    private static final Long TELEGRAM_CHAT_VOLUNTEERS = -844733515L;


    private final TelegramBot telegramBot;

    private final AnimalShelterKeyboard animalShelterKeyboard;
    private final Logger logger = LoggerFactory.getLogger (TelegramBotUpdatesListener.class);

    public TelegramBotUpdatesListener(TelegramBot telegramBot, AnimalShelterKeyboard animalShelterKeyboard) {
        this.telegramBot = telegramBot;
        this.animalShelterKeyboard = animalShelterKeyboard;
    }

    private boolean isCat = false;

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
                    case START_COMMAND:
                        sendMessage(chatId , GREETING_TEXT);
                        animalShelterKeyboard.chooseMenu(chatId);
                        break;
                    case "Приют для кошек":
                        isCat = true;
                        animalShelterKeyboard.sendMenu(chatId);
                        sendMessage(chatId, "Вы выбрали приют для кошек");
                        break;
                    case "Приют для собак":
                        isCat = false;
                        animalShelterKeyboard.sendMenu(chatId);
                        sendMessage(chatId, "Вы выбрали приют для собак");
                        break;
                    case "Главное меню":
                        animalShelterKeyboard.chooseMenu(chatId);
                        break;
                    case "Узнать информацию о приюте":
                        animalShelterKeyboard.sendMenuInformationAboutShelter(chatId);
                        break;
                    case "Информация о приюте":
                        if (isCat) {
                            sendMessage(chatId, INFORMATION_ABOUT_CAT_SHELTER);
                            break;
                        } else {
                            sendMessage(chatId, INFORMATION_ABOUT_DOG_SHELTER);
                        }
                        break;
                    case "Советы и рекомендации":
                        if (isCat) {
                            sendMessage(chatId, INFORMATION_ABOUT_CATS);
                            break;
                        } else {
                            sendMessage(chatId, INFORMATION_ABOUT_DOGS);
                            break;
                        }
                    case "Как взять животное из приюта?":
                        animalShelterKeyboard.sendMenuHowToTakeAnAnimal(chatId);
                        break;
                    case "Прислать отчет о питомце":
                        sendMessage(chatId, INFORMATION_ABOUT_REPORT);
                        sendMessage(chatId, REPORT_EXAMPLE);
                        break;
                    case "Информация о возможностях бота":
                        sendMessage(chatId, INFORMATION_ABOUT_BOT);
                        break;
                    case "Вернуться в меню":
                        animalShelterKeyboard.sendMenu(chatId);
                        break;
                    case "Позвать добровольца":
                        sendMessage(chatId, "Я передал ваше сообщение добровольцам. " +
                                  "Если у вас закрытый профиль - поделитесь контактом. " +
                                  "Справа сверху 3 точки - отправить свой телефон");
                        sendForwardMessage(chatId, update.message().messageId());
                        break;
                    case "Привет":
                        if (update.message().messageId() != null) {
                            sendReplyMessage(chatId, "Приветствую", update.message().messageId());
                            break;
                        }
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