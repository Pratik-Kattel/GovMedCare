package com.govmedcare.service;
import com.govmedcare.dao.MedicineDao;
import com.govmedcare.exception.MedicineAlreadyExistsException;
import com.govmedcare.model.Medicine;
import java.util.List;

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
    public List<Medicine> getAllMedicines(){
        try{
            return medicineDao.getAllMedicine();
        }
        catch (Exception e){
            throw new RuntimeException("Unable to fetch the medicines");
        }
    }
    public List<Medicine> getAllMedicineByCategory(Long category_id){
        if(category_id==null || category_id<=0) throw new IllegalArgumentException("Invalid category id");
        try{
            return medicineDao.getAllMedicineByCategory(category_id);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to fetch the medicines by category");
        }
    }
    public int getPendingCount() {
        return medicineDao.countPendingMedicines();
    }

    public int getApprovedCount() {
        return medicineDao.countApprovedMedicines();
    }
}
