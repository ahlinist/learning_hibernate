package hello;

import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

    private static final String DEFAULT_MESSAGE = "message";
    private static final String DEFAULT_NEXT_MESSAGE = "next message";

    @Test
    public void testMessageCreation() {
        Message nextMessage = new Message();
        nextMessage.setId(2L);
        nextMessage.setText(DEFAULT_NEXT_MESSAGE);

        Message message = new Message();
        message.setId(1L);
        message.setText(DEFAULT_MESSAGE);
        message.setNextMessage(nextMessage);

        Assert.assertEquals(DEFAULT_MESSAGE, message.getText());
        Assert.assertEquals(nextMessage, message.getNextMessage());
    }
}
