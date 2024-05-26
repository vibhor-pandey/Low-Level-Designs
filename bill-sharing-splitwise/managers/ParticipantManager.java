package managers;

import models.Participant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantManager {
    private final Map<String, Participant> participantMap;

    public ParticipantManager() {
        this.participantMap = new HashMap<>();
    }

    public void createParticipant(final String name, final String email) {
        //Validation can be added
        participantMap.put(name, new Participant(name, email));
    }

    public List<Participant> getAllParticipants() {
        return this.participantMap.values().stream().toList();
    }

    public Participant getParticipant(final String name) {
        return participantMap.getOrDefault(name, null);
    }
}
