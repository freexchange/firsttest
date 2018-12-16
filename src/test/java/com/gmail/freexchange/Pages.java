package com.gmail.freexchange;

import java.util.Date;

public class Pages {
        private String status;
        private String dateOfTest;
        private String pageName;
        private String pageTitle;
        private String pageTitleStatus;
        private String url;
        private String company;
        private String companyStatus;
        private int companyTimes;
        private String companyTimesStatus;
        private String address;
        private String addressStatus;
        private int addressTimes;
        private String addressTimesStatus;
        private String email;
        private String emailStatus;
        private int emailTimes;
        private String emailTimesStatus;
        private String tfn;
        private String tfnStatus;
        private int tfnTimes;
        private String tfnTimesStatus;
        private String descriptor;
        private String descriptorStatus;
        private String screenshotName;

        public Pages(String status, String dateOfTest, String pageName, String pageTitle, String pageTitleStatus,
                     String url, String company, String companyStatus, int companyTimes, String companyTimesStatus,
                     String address, String addressStatus, int addressTimes, String addressTimesStatus, String email,
                     String emailStatus, int emailTimes, String emailTimesStatus, String tfn, String tfnStatus,
                     int tfnTimes, String tfnTimesStatus, String descriptor, String descriptorStatus,
                     String screenshotName) {
            this.status = status;
            this.pageTitle = pageTitle;
            this.pageTitleStatus = pageTitleStatus;
            this.dateOfTest = dateOfTest;
            this.pageName = pageName;
            this.url = url;
            this.company = company;
            this.companyStatus = companyStatus;
            this.companyTimes = companyTimes;
            this.companyTimesStatus = companyTimesStatus;
            this.address = address;
            this.addressStatus = addressStatus;
            this.addressTimes = addressTimes;
            this.addressTimesStatus = addressTimesStatus;
            this.email = email;
            this.emailStatus = emailStatus;
            this.emailTimes = emailTimes;
            this.emailTimesStatus = emailTimesStatus;
            this.tfn = tfn;
            this.tfnStatus = tfnStatus;
            this.tfnTimes = tfnTimes;
            this.tfnTimesStatus = tfnTimesStatus;
            this.descriptor = descriptor;
            this.descriptorStatus = descriptorStatus;
            this.screenshotName = screenshotName;
        }

    public Pages() {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageTitleStatus() {
        return pageTitleStatus;
    }

    public void setPageTitleStatus(String pageTitleStatus) {
        this.pageTitleStatus = pageTitleStatus;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyTimesStatus() {
        return companyTimesStatus;
    }

    public void setCompanyTimesStatus(String companyTimesStatus) {
        this.companyTimesStatus = companyTimesStatus;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }

    public String getAddressTimesStatus() {
        return addressTimesStatus;
    }

    public void setAddressTimesStatus(String addressTimesStatus) {
        this.addressTimesStatus = addressTimesStatus;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getEmailTimesStatus() {
        return emailTimesStatus;
    }

    public void setEmailTimesStatus(String emailTimesStatus) {
        this.emailTimesStatus = emailTimesStatus;
    }

    public String getTfnStatus() {
        return tfnStatus;
    }

        public void setTfnStatus(String tfnStatus) {
        this.tfnStatus = tfnStatus;
    }

    public String getTfnTimesStatus() {
        return tfnTimesStatus;
    }

    public void setTfnTimesStatus(String tfnTimesStatus) {
        this.tfnTimesStatus = tfnTimesStatus;
    }

    public String getDescriptorStatus() {
        return descriptorStatus;
    }

    public void setDescriptorStatus(String descriptorStatus) {
        this.descriptorStatus = descriptorStatus;
    }

    public String getDateOfTest() {
        return dateOfTest;
    }

    public String getPageTitle() {
        return pageTitle;
    }
    public String getStatus() {
        return status;
    }

    public void setDateOfTest(String dateOfTest) {
        this.dateOfTest = dateOfTest;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCompanyTimes() {
        return companyTimes;
    }

    public void setCompanyTimes(int companyTimes) {
        this.companyTimes = companyTimes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressTimes() {
        return addressTimes;
    }

    public void setAddressTimes(int addressTimes) {
        this.addressTimes = addressTimes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailTimes() {
        return emailTimes;
    }

    public void setEmailTimes(int emailTimes) {
        this.emailTimes = emailTimes;
    }

    public String getTfn() {
        return tfn;
    }

    public void setTfn(String tfn) {
        this.tfn = tfn;
    }

    public int getTfnTimes() {
        return tfnTimes;
    }

    public void setTfnTimes(int tfnTimes) {
        this.tfnTimes = tfnTimes;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getScreenshotName() {
        return screenshotName;
    }

    public void setScreenshotName(String screenshotName) {
        this.screenshotName = screenshotName;
    }
}
