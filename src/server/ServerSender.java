package server;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;

import Logger.Logger;

public class ServerSender extends Thread{

	OutputStream outputStream;
	Service service;
	Logger logger = Logger.getLogger();

	public ServerSender(OutputStream outputStream, Service service) {
		this.outputStream = outputStream;
		this.service = service;
	}

	@Override
	public void run() {
		PrintWriter printer = new PrintWriter(outputStream, true);
		Gson gson = new Gson();

		while(true) {

			String serverMessage = gson.toJson(service.getServerMessages());
			printer.println(serverMessage);
			printer.flush();
			printer.flush();
		

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



		}
	}

}
