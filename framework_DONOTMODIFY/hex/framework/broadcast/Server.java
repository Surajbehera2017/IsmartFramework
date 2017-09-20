/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	private static DatagramSocket c;
	private static DatagramSocket socket;
	static Runnable send;

	public static void main(String[] args) {
		// new Client().Client1();

		Runnable receive = () -> {
			try {
				// Keep a socket open to listen to all the UDP trafic that is
				// destined for this port
				socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
				socket.setBroadcast(true);

				while (true) {
					// System.out.println(getClass().getName() + ">>>Ready to
					// receive broadcast packets!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Receive a packet
					byte[] recvBuf = new byte[15000];
					DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
					socket.receive(packet);

					// See if the packet holds the right command (message)
					String message = new String(packet.getData()).trim();
					if (message.equals("AUTOMATION")) {

						String hostname = packet.getAddress().getHostAddress();
						// Packet received

						// System.out.println(">>>Discovery packet received
						// from: " +hostname );

						// System.out.println(">>>Packet received; data: " + new
						// String(packet.getData()));

					}
				}
			} catch (IOException ex) {
			}
		};

		// Thread t = new Thread(receive);
		//
		// t.start();
		Thread t1 = new Thread(send);

		t1.start();

		send = () -> {

			boolean btrue = true;
			while (btrue) {

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Try the 255.255.255.255 first
				try {
					c = new DatagramSocket();
					c.setBroadcast(true);
					byte[] sendData = "AUTOMATION".getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
							InetAddress.getByName("255.255.255.255"), 8888);
					c.send(sendPacket);
					// System.out.println(getClass().getName() + ">>> Request
					// packet sent to: 255.255.255.255 (DEFAULT)");
				} catch (Exception e) {
				}

			}

		};

	}

}
