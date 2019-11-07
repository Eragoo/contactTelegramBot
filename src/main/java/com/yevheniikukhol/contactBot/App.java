package com.yevheniikukhol.contactBot;

import com.yevheniikukhol.contactBot.res.Bot;

public class App {
	
    public static void main(String[] args){
    	new App().start();
    }
    
    public App() {
    	
	}
    
    public void start() {
        Bot bot = new Bot();
        bot.Start();
	}
    
}
