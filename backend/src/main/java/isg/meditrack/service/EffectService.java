package isg.meditrack.service;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Entry;
import isg.meditrack.exception.NotFoundException;

import java.util.List;

public interface EffectService {

    /**
     * Create an effect with a certain id.
     *
     * @param newEff  new effect that should be added to the database
     * @param entry  the entry the effect is linked to
     */
    Effect create(Effect newEff, Entry entry);

    /**
     * Create an effect with a certain id.
     *
     * @param updatedEff  new effect that should be added to the database
     * @param entry  the entry the effect is linked to
     */
    Effect update(Effect updatedEff, Entry entry);

    /**
     * Get all effects for a specific medication.
     *
     * @param medId ID of the medication
     */
    List<Effect> getByMedication(Long medId);

    /**
     * Get all effects for a specific entry.
     *
     * @param entryId ID of the entry
     */
    List<Effect> getByEntry(Long entryId);

    /**
     * Get an effect with a certain id.
     *
     * @param id  id of the effect that is looked for
     */
    Effect getById(Long id);

    /**
     * Get all distinct effect names for a user
     *
     */
    List<String> getAllEffectNames();

    /**
     * Get all effects for a specific name.
     *
     * @param name Name of the entry
     */
    List<Effect> getByName(String name);

    /**
     * Delete all effects of an entry
     *
     * @param entryId of the entry
     */
    void deleteForEntry(Long entryId);
}
