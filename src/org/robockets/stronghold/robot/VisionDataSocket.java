package org.robockets.stronghold.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionDataSocket extends Thread {

	ServerSocket serverSocket;
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	
	NetworkTable table;
	
	public VisionDataSocket() {
		try {
			this.serverSocket = new ServerSocket(123);
		} catch (Exception e) {
			e.printStackTrace();
			// This be bad
		}
	}
	
	private void connectToClient() {
		try {
			this.clientSocket = serverSocket.accept();
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			// This be bad
		}
	}
	
	private void connectToTable() {
		table = NetworkTable.getTable("vision");
	}
	
	private HashMap<String, String> getVisionData() {
		HashMap<String, String> data = new HashMap<String, String>();
		
		if (table.getNumber("can_see_target", 0) == 1) {
			data.put("lock", "00cc00");
		} else {
			data.put("lock", "cc3300");
		}
		
		return data;
	}
	
	private void sendVisionData(HashMap<String, String> dataArray) {
		Iterator<Entry<String, String>> it = dataArray.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry<String, String> data = (Entry<String, String>) it.next();
	    	out.println(data.getKey() + "," + data.getValue());
	    }
	}
	
	public void run() {
		connectToTable();
		
		while (true) {
			connectToClient();
			while (!out.checkError()) { // If there is a disconnect then keep connect again
				sendVisionData(getVisionData());
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
