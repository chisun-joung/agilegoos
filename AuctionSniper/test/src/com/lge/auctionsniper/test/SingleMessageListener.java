package com.lge.auctionsniper.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SingleMessageListener implements MessageListener {
	private final ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(1);
	
	@Override
	public void processMessage(Chat chat, Message message) {
		messages.add(message);
		
	}

	public void receivesAMessage() throws InterruptedException {
		final Message message = messages.poll(5, TimeUnit.SECONDS);
		assertThat(message,is(notNullValue()));
		
	}

}
