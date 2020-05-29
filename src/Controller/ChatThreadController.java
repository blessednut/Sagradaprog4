package Controller;

import View.ChatView;
import javafx.application.Platform;
import model.ChatModel;

public class ChatThreadController extends Thread{
	private ChatModel ChatM;
	private ChatView ChatV;
	private String message;
	private int idgame;

	
	private boolean running;

	
	public ChatThreadController(ChatModel ChatM, ChatView ChatV, int idgame) {
		this.idgame = idgame;
		this.ChatM = ChatM;
		this.ChatV = ChatV;
		running = true;
		message = "";
	}


	@Override
	public void run() {
		while(running) {
			try {
				String newMessage = ChatM.getUsername(idgame) + " :" + ChatM.getMessage(idgame);

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if(!message.equals(newMessage)) {
							message = newMessage;
							ChatV.setRecentChat(message);
						}

					}
				});
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
	}
	public void terminate() {
		running = false;
	}
	public ChatModel getModel() {
		return ChatM;
	}
}