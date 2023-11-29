package isg.meditrack.service;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Entry;

public interface EffectService {

    /**
     * Create an effect with a certain id.
     *
     * @param newEff  new effect that should be added to the database
     * @param entry  the entry the effect is linked to
     */
    Effect create(Effect newEff, Entry entry);
}
