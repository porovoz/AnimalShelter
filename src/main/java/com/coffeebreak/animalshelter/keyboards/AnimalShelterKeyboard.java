package com.coffeebreak.animalshelter.keyboards;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Service;

@Service
public class AnimalShelterKeyboard {
      private final TelegramBot telegramBot;

      public AnimalShelterKeyboard(TelegramBot telegramBot) {
            this.telegramBot = telegramBot;
      }

      public void chooseMenu(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Приют для кошек"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Приют для собак"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Выберите кого хотите приютить:");
      }

      public void sendMenu(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Информация о возможностях бота"),
                      new KeyboardButton("Узнать информацию о приюте"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Как взять животное из приюта?"),
                      new KeyboardButton("Прислать отчет о питомце"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать добровольца"), new KeyboardButton("Главное меню"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Главное меню");
      }

      public void sendMenuInformationAboutShelter(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("Информация о приюте"),
                      new KeyboardButton("Оставить контактные данные").requestContact(true));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать добровольца"),
                      new KeyboardButton("Вернуться в меню"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Информация о приюте");
      }

      public void sendMenuHowToTakeAnAnimal(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Советы и рекомендации"),
                      new KeyboardButton("Оставить контактные данные").requestContact(true));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать добровольца"),
                      new KeyboardButton("Вернуться в меню"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Как взять питомца из приюта");
      }

      public void returnResponseReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String text) {
            replyKeyboardMarkup.resizeKeyboard(true);
            replyKeyboardMarkup.oneTimeKeyboard(false);
            replyKeyboardMarkup.selective(false);

            SendMessage request = new SendMessage(chatId, text)
                      .replyMarkup(replyKeyboardMarkup)
                      .parseMode(ParseMode.HTML)
                      .disableWebPagePreview(true);
            SendResponse sendResponse = telegramBot.execute(request);

            if (!sendResponse.isOk()) {
                  int codeError = sendResponse.errorCode();
                  String description = sendResponse.description();
            }
      }
}