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
}
