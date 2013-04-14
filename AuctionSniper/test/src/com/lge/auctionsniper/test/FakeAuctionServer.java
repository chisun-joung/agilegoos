package com.lge.auctionsniper.test;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class FakeAuctionServer {

	private static final String XMPP_HOSTNAME = "localhost";
	private static final String ITEM_ID_AS_LOGIN = "auction-%s";
	private static final String AUCTION_PASSWORD = "auction";
	private static final String AUCTION_RESOURCE = "Auction";
	private final String itemId;
	private final XMPPConnection connection;

	public FakeAuctionServer(String itemId) {
		this.itemId = itemId;
		this.connection = new XMPPConnection(XMPP_HOSTNAME);
	}

	public void startSellingItem() throws XMPPException {
		connection.connect();
		connection.login(String.format(ITEM_ID_AS_LOGIN, itemId), AUCTION_PASSWORD, AUCTION_RESOURCE);
		connection.getChatManager().addChatListener(new ChatManagerListener() {
			
			private Chat currentChat;

			@Override
			public void chatCreated(Chat chat, boolean createdLocally) {
				currentChat = chat;
			}
		});
	}

	
	public void hasReceivedJoinRequestFromSniper() {
		// TODO Auto-generated method stub
		
	}

	public void announceClosed() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
