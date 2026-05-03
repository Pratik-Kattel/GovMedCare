package com.govmedcare.service;

import com.govmedcare.dao.MedicineDao;
import com.govmedcare.exception.MedicineAlreadyExistsException;
import com.govmedcare.model.Medicine;

public class MedicineService {
    MedicineDao medicineDao = new MedicineDao();

    public boolean saveMedicineService(Medicine medicine,Long user_id) {
        boolean existsByName = medicineDao.existByName(medicine.getName());
        if(existsByName){
            throw new MedicineAlreadyExistsException("This medicine already exists please add the quantity");
        }
        medicine.setIs_verified(false);
        return  medicineDao.SaveMedicine(medicine);
    }
}
