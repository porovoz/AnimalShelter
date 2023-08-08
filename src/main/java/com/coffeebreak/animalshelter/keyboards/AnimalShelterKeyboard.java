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
//        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Выберите кого хотите приютить:");
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Добро пожаловать! Я бот приюта для животных. Выберите приют: Приют для кошек или Приют для собак.");
      }

      public void sendMenuCatShelter(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Узнать информацию о приюте"), new KeyboardButton("Как взять кота из приюта"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Выбрать кота"), new KeyboardButton("Прислать отчет о питомце"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтёра"), new KeyboardButton("Главное меню"));
//        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Главное меню");
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Вы выбрали приют для кошек");
      }
      public void sendMenuDogShelter(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Узнать информацию о приюте"), new KeyboardButton("Как взять собаку из приюта"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Выбрать собаку"), new KeyboardButton("Прислать отчет о питомце"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтёра"), new KeyboardButton("Главное меню"));
//        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Главное меню");
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Вы выбрали приют для собак");
      }

      public void sendMenuInformationAboutShelter(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Информация о приюте"));
//                , new KeyboardButton("Оставить контактные данные").requestContact(true));
            replyKeyboardMarkup.addRow(new KeyboardButton("Расписание"), new KeyboardButton("Оформление пропуска"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Техника безопасности"), new KeyboardButton("Позвать волонтёра"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Главное меню"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Какая информация вас интересует?");
      }


      public void sendMenuHowToTakeCat(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Важная информация"),
                      new KeyboardButton("Правила знакомства"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Список документов"),
                      new KeyboardButton("Рекомендации к транспортировке"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Обустройство жилья котёнка"),
                      new KeyboardButton("Обустройство жилья взрослого кота"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Обустройство жилья кота с инвалидностью"),
                      new KeyboardButton("Причины по которым можем не выдать питомца"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтёра"),
                      new KeyboardButton("Вернуться в меню"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Мы очень рады что вы хотите завести нового друга. Выберите что вас интересует:");
      }
      public void sendMenuHowToTakeDog(Long chatId) {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                      new KeyboardButton("Важная информация"),
                      new KeyboardButton("Правила знакомства"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Список документов"),
                      new KeyboardButton("Рекомендации к транспортировке"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Обустройство жилья щенка"),
                      new KeyboardButton("Обустройство жилья взрослой собаки"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Обустройство жилья собаки с инвалидностью"),
                      new KeyboardButton("Причины по которым можем не выдать питомца"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Советы кинолога"),
                      new KeyboardButton("Контакты проверенных кинологов"));
            replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтёра"),
                      new KeyboardButton("Вернуться в меню"));
            returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Мы очень рады что вы хотите завести нового друга. Выберите что вас интересует:");
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