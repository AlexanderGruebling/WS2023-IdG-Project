package isg.meditrack.service;

import isg.meditrack.entity.Effect;
import isg.meditrack.exception.ValidationException;

public interface EffectService {

    /**
     * Create an effect with a certain id.
     *
     * @param newEff  new effect that should be added to the database
     * @param entryId the id of the entry the effect is linked to
     */
    Effect create(Effect newEff, Long entryId);
}
