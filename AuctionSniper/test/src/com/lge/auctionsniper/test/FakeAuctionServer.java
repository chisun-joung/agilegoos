package com.lge.auctionsniper.test;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class FakeAuctionServer {

	private static final String XMPP_HOSTNAME = "localhost";
	private static final String ITEM_ID_AS_LOGIN = "auction-%s";
	private static final String AUCTION_PASSWORD = "auction";
	private static final String AUCTION_RESOURCE = "Auction";
	private final String itemId;
	private final XMPPConnection connection;
	private final SingleMessageListener messageListener = new SingleMessageListener();
	private Chat currentChat;
	
	public FakeAuctionServer(String itemId) {
		this.itemId = itemId;
		ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(XMPP_HOSTNAME, 5222);
		this.connection = new XMPPConnection(connectionConfiguration);
	}

	public void startSellingItem() throws XMPPException {
		connection.connect();
		connection.login(String.format(ITEM_ID_AS_LOGIN, itemId), AUCTION_PASSWORD, AUCTION_RESOURCE);
		connection.getChatManager().addChatListener(new ChatManagerListener() {
			

			@Override
			public void chatCreated(Chat chat, boolean createdLocally) {
				currentChat = chat;
				chat.addMessageListener(messageListener);
			}
		});
	}

	
	public void hasReceivedJoinRequestFromSniper() throws InterruptedException {
		messageListener.receivesAMessage();
	}

	public void announceClosed() throws XMPPException {
		currentChat.sendMessage(new Message());
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
