package com.govmedcare.service;

import com.govmedcare.dao.ApproveMedicineDao;
import com.govmedcare.model.Medicine;

import java.util.List;

public class ApproveMedicineService {
    ApproveMedicineDao approveMedicineDao=new ApproveMedicineDao();

    public List<Medicine> getPendingMedicines(){
        try{
            return approveMedicineDao.getPendingMedicines();
        }
        catch (Exception e){
            throw new RuntimeException("Unable to fetch the medicines");
        }
    }
    public boolean approveMedicine(int id){
        if(id<0){
            throw new IllegalArgumentException("Invalid medicine id");
        }
        try {
            return approveMedicineDao.approveMedicine(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to approve medicine, please try again later !!");
        }
    }
    public boolean deleteMedicine(int id){
        if(id<0){
            throw new IllegalArgumentException("Invalid medicine id");
        }
        try{
            return approveMedicineDao.deleteMedicine(id);
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to delete medicine, please try again later !!");
        }

    }
}
