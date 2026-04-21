package com.govmedcare.repository;

import com.govmedcare.model.Medicine;

public interface MedicineRepository {
    boolean SaveMedicine(Medicine medicine);
    boolean existByName(String name);
}
