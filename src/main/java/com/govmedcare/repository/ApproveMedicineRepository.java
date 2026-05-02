package com.govmedcare.repository;

import com.govmedcare.model.Medicine;
import java.util.List;

public interface ApproveMedicineRepository {

    List<Medicine> getPendingMedicines();

    boolean approveMedicine(int id);

    boolean deleteMedicine(int id);
}