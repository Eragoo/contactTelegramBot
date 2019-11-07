package com.yevheniikukhol.contactBot.res;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.ForwardMessage;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

public class Bot {
    private int updateId = 0;
    private final String TOKEN = TOKEN_BOT;
    private Update update;
    private TelegramBot bot;
    private long ownerId = YOUR_CHAT_ID_HERE;

    public Bot() {
    }
    public void Start() {
        bot = new TelegramBot(this.TOKEN);
        try{
            Test();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void Test() {
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                System.out.println(update.toString());
                long chatId = update.message().chat().id();

                if (chatId == ownerId && update.message().replyToMessage().forwardFrom() != null){
                    bot.execute(new SendMessage(update.message().replyToMessage().forwardFrom().id(), update.message().text()));
                }else {
                    bot.execute(new ForwardMessage(ownerId, chatId, update.message().messageId()));
                }

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
