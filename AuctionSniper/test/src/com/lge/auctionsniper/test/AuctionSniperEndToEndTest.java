package com.lge.auctionsniper.test;

import com.jayway.android.robotium.solo.Solo;
import com.lge.auctionsniper.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class AuctionSniperEndToEndTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
	private final ApplicationRunner application = new ApplicationRunner();
	private Solo solo;
	private XmppServer server = new XmppServer();
	public AuctionSniperEndToEndTest() {
		super(MainActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		server.start();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	public void testSniperJoinsAuctionUnitlAuctionCloses() throws Exception {
		auction.startSellingItem();
		application.startBiddingIn(auction,solo);
		auction.hasReceivedJoinRequestFromSniper();
		auction.announceClosed();
		application.showsSniperHasLostAuction();
	}
	
	@Override
	public void tearDown() throws Exception {
		auction.stop();
		application.stop();
		server.stop();
		super.tearDown();
	}
}
