package com.edu.smu.track2career.beans;

import com.edu.smu.track2career.manager.ExcelExtractorManager;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.*;

@ManagedBean(name = "bootstrap", eager = true)
@RequestScoped
public class BootstrapBean {

    private Part file;
    private String successMessage;
    private String errorMessage;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String processFile() {

        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                // Read the uploaded excel file
                Workbook workbook = ExcelExtractorManager.streamToWorkbook(input);

                // Retrieve the first worksheet in the file
                Sheet sheet = workbook.getSheetAt(0);

//                int lastRowIndex = sheet.getLastRowNum();
                int lastRowIndex = sheet.getPhysicalNumberOfRows();
                int contentRowIndex = ExcelExtractorManager.extractHeader(sheet);
                ExcelExtractorManager.extractContent(sheet, lastRowIndex, contentRowIndex);
                successMessage = "Data uploaded successfully!";
            } catch (IOException e) {
                successMessage = "Data failed to be uploaded!";
            }
        }
        return "";
    }

}
