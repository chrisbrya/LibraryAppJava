package com.library.system.service;

import com.library.system.Model.Requests;
import com.library.system.interfaces.RequestsInterface;

import java.util.ArrayList;

public class RequestsInterfaceImpl implements RequestsInterface {

    int requestCount = 0;
    ArrayList<Requests> requestsList = new ArrayList<>();

    @Override
    public void generateRequest(String userId, String ibn) {
        requestsList.add(new Requests(userId, ibn, 'p'));

    }

    @Override
    public int getRequestByUserId(String userId) {
        int index = -1;
        for (Requests currentRequests : requestsList) {
            index++;
            if(currentRequests.getUserId().equals(userId)){
                return index;
            }
        }
        return -1;
    }

    @Override
    public int getRequestIndexByIbnNumber(String ibn) {
        int index = -1;
        for (Requests currentRequests : requestsList) {
            index++;
            if(currentRequests.getIbn().equals(ibn)){
                return index;
            }
        }
        return -1;
    }

    @Override
    public Requests getRequest(int requestIndex) {
        return requestsList.get(requestIndex);
    }

    @Override
    public void approveOrDeny(int requestIndex, char approveOrDenied) {

        Requests requests = requestsList.get(requestIndex);
        requests.setApproveOrDeny(approveOrDenied);
        requestsList.set(requestIndex, requests);

    }

    @Override
    public void removeRequest(int requestIndex) {
        this.requestsList.remove(requestIndex);
    }
}
