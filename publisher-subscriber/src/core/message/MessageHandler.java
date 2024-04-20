package core.message;

import core.message.model.Message;

import javax.validation.constraints.NotNull;

public interface MessageHandler {

    Message wrapMessage(@NotNull final String message);

    String unwrapMessage(@NotNull final Message message);
}
