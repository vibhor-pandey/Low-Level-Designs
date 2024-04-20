package core.message.model;

import lombok.Data;

@Data
public class Message {

    private String id;

    private Long timestamp;

    private int timeToLiveInMillis;

    private String data;
}
