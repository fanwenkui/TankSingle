package com.fwk.game;

import com.fwk.game.net.Client;
import com.fwk.game.tank.Audio;
import com.fwk.game.tank.TankFrame;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = TankFrame.INSTANCE;
		tf.setVisible(true);
		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		
		new Thread(()->{while(true) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tf.repaint();
		}}).start();

		Client client=new Client();
		client.connect();
	}

}
