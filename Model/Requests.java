package com.library.system.Model;

public class Requests {

    private String userId;
    private String ibn;
    private char approveOrDeny;

    public Requests(String userId, String ibn, char approveOrDeny){
        this.userId = userId;
        this.ibn = ibn;
        this.approveOrDeny = approveOrDeny;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIbn() {
        return ibn;
    }

    public void setIbn(String ibn) {
        this.ibn = ibn;
    }

    public char isApproveOrDeny() {
        return approveOrDeny;
    }

    public void setApproveOrDeny(char approveOrDeny) {
        this.approveOrDeny = approveOrDeny;
    }

    @Override
    public String toString() {
        return "Requests\n" +
                " userId = '" + userId + '\'' +
                ", ibn = '" + ibn + '\'' +
                ", approveOrDeny = " + approveOrDeny;
    }

    public String convertStatusToString(){
        switch (this.approveOrDeny){
            case 'a':
                return "Approved";
            case 'd':
                return "Denied";
            default:
                return "Pending";
        }
    }
}
