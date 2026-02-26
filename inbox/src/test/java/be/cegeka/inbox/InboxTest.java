package be.cegeka.inbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

public class InboxTest {

    private MessageSenderForTest messageSender = new MessageSenderForTest();

    @BeforeEach
    public void setUp() throws Exception {
        MessageSender.setInstance(messageSender);
    }

    @Test
    public void whenMessageArrivesThenSendMessageToReception() throws Exception {
        Inbox inbox = new Inbox();

        inbox.receiveMessage("Ser Pounce", "Hello Cegeka!");

        assertThat(messageSender.messages).containsExactly(entry("reception", "Hello Cegeka!"));
    }

    @Test
    public void whenMessageContainingCVArrivesThenSendMessageToRecruitment() throws Exception {
        Inbox inbox = new Inbox();

        inbox.receiveMessage("Ser Pounce", "Hello Cegeka! CV: 123456789");

        assertThat(messageSender.messages).containsExactly(entry("recruitment", "Hello Cegeka! CV: 123456789"));
    }

    private static class MessageSenderForTest extends MessageSender {
        Map<String, String> messages = new HashMap<>();

        void acceptMessage(String to, String message) {
            messages.put(to, message);
        }
    }

    @Test
    public void whenMessageContainingPromoArrivesThenSendMessageToSpam() throws Exception {
        Inbox inbox = new Inbox();

        inbox.receiveMessage("Ser Pounce", "Hello Cegeka! Promo: 123456789");

        assertThat(messageSender.messages).containsExactly(entry("spam", "Hello Cegeka! Promo: 123456789"));
    }

    @Test
    public void whenMessageContainingAdvertisementArrivesThenSendMessageToSpam() throws Exception {
        Inbox inbox = new Inbox();

        inbox.receiveMessage("Ser Pounce", "Hello Cegeka! Advertisement: 123456789");

        assertThat(messageSender.messages).containsExactly(entry("spam", "Hello Cegeka! Advertisement: 123456789"));
    }

    @Test
    public void whenMessageContainingProposalArrivesThenSendMessageToSales() throws Exception {
        Inbox inbox = new Inbox();

        inbox.receiveMessage("Ser Pounce", "Hello Cegeka! Proposal: 123456789");

        assertThat(messageSender.messages).containsExactly(entry("sales", "Hello Cegeka! Proposal: 123456789"));
    }
}
