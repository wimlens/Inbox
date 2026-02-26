package be.cegeka.inbox;

import static be.cegeka.inbox.MessageSender.sendMessage;

public class Inbox {

    public void receiveMessage(String from, String message) {
        if (message.toLowerCase().contains("cv")) {
            sendMessage("recruitment", message);
        } else if (message.toLowerCase().contains("promo") || message.toLowerCase().contains("advertisement")) {
            sendMessage("spam", message);
        } else if (message.toLowerCase().contains("proposal")) {
            sendMessage("sales", message);
        } else {
            sendMessage("reception", message);
        }
    }
}
