package core.message;

import core.message.model.Message;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageHandlerImpl implements MessageHandler {

    private static final int DEFAULT_TTL_IN_MILLIS = 5_000;

    @Override
    public Message wrapMessage(String message) {
        Message msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setTimestamp(Instant.now().toEpochMilli());
        msg.setTimeToLiveInMillis(DEFAULT_TTL_IN_MILLIS);
        msg.setData(message);
        return msg;
    }

    @Override
    public String unwrapMessage(Message message) {
        if((OffsetDateTime.now().toEpochSecond() + (long) DEFAULT_TTL_IN_MILLIS) > message.getTimestamp()) {
            System.out.println("Message - " + message + "\nEXPIRED");
            return "";
        }
        return message.getData();
    }
}
