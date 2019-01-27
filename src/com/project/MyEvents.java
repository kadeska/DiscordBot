package com.project;

import java.io.IOException;
import java.security.GeneralSecurityException;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class MyEvents implements IListener<Event> {

	@Override
	public void handle(Event event) {
		if (event instanceof MessageReceivedEvent) {
			MessageEvent ev = (MessageReceivedEvent) event;
			// suggest command
			if ((((MessageReceivedEvent) event).getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "suggest"))) {
				if (((MessageReceivedEvent) event).getChannel().getName().equalsIgnoreCase("suggestions")) {
					String message = ev.getMessage().getContent().substring(8);
					try {
						GoogleSheetManager.update("Suggestions", ev.getAuthor().getName(), message,
								ev.getMessage().getTimestamp().toString());
					} catch (IOException | GeneralSecurityException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					BotUtils.sendMessage(((MessageEvent) event).getChannel(), "I have sent your suggestions");
				}
			}
			
			// report-bug command
			if ((((MessageReceivedEvent) event).getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "report-bug"))) {
				if (((MessageReceivedEvent) event).getChannel().getName().equalsIgnoreCase("bug_reports")) {
					String message = ev.getMessage().getContent().substring(11);
					try {
						GoogleSheetManager.update("Bug_reports", ev.getAuthor().getName(), message,
								ev.getMessage().getTimestamp().toString());
					} catch (IOException | GeneralSecurityException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					BotUtils.sendMessage(((MessageEvent) event).getChannel(), "I have sent your bug report");
				}
			}

			// help command
			if ((((MessageReceivedEvent) event).getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "help"))) {
				if (((MessageReceivedEvent) event).getChannel().getName().equalsIgnoreCase("bot_commands")) {
					BotUtils.sendMessage(((MessageEvent) event).getChannel(),
							"This is the help command. List of commands: help, suggest");
				}
			}
		}
	}

}
