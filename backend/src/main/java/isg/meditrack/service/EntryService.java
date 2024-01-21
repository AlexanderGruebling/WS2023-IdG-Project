package isg.meditrack.service;

import isg.meditrack.entity.Entry;
import isg.meditrack.exception.NotFoundException;

import java.util.List;

public interface EntryService {

    /**
     * Create an entry with a certain id.
     *
     * @param newEntry new entry that should be added to the database
     */
    Entry create(Entry newEntry);

    /**
     * Update an entry with a certain id.
     *
     * @param updatedEntry new entry that should be updated in the database
     */
    Entry update(Entry updatedEntry);

    /**
     * Get an entry with a certain id.
     *
     * @param id  id of the entry that is looked for
     * @throws NotFoundException if medication with given id doesn't exist
     */
    Entry getById(Long id) throws NotFoundException;

    /**
     * Get all entries for the user sending the request.
     */
    List<Entry> getByUser();

    /**
     * Get the last entry for the user sending the request.
     */
    Entry getLastByUser();

    /**
     * Get all entries for a medication.
     */
    List<Entry> getByMedId(Long medId);


    /**
     * Delete entry with certain id.
     */
    void delete(Long entryId);
}
