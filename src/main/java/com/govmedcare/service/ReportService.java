package com.govmedcare.service;

import com.govmedcare.dao.ReportDao;
import com.govmedcare.model.Report;

public class ReportService {
    private ReportDao reportDao = new ReportDao();

    public Report generateReport() {
        double revenue = reportDao.generateReport();
        double tax = revenue * 0.13;
        Report report = new Report();
        report.setRevenue(revenue);
        report.setTaxAmount(tax);
        return report;
    }
}
