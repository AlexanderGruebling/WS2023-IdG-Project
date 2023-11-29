package isg.meditrack.service;


import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.exception.ValidationException;

public interface MedicationService {

    /**
     * Create a medication with a certain id.
     *
     * @param newMed  new medication that should be added to the database
     * @throws ValidationException if medication has invalid values
     */
    Medication create(Medication newMed) throws ValidationException;

    /**
     * Get a medication with a certain id.
     *
     * @param id  id of medication that is looked for
     * @throws NotFoundException if medication with given id doesn't exist
     */
    Medication getById(Long id) throws NotFoundException;
}
