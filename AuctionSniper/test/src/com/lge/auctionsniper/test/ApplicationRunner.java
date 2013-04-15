package com.lge.auctionsniper.test;

import org.jivesoftware.smack.XMPPException;

import com.jayway.android.robotium.solo.Solo;
import com.lge.auctionsniper.MainActivity;

public class ApplicationRunner {

	private AuctionSniperDriver driver;

	public void startBiddingIn(FakeAuctionServer auction, Solo solo) {
		MainActivity mainActivity = (MainActivity)solo.getCurrentActivity();
		try {
			mainActivity.join("localhost","sniper","sniper","item-54321");
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		driver = new AuctionSniperDriver(solo,1000);
		driver.showsSniperStatus("Joining");
		
	}

	public void showsSniperHasLostAuction() {
		driver.showsSniperStatus("Lost");
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
