package com.library.system.interfaces;

import com.library.system.Model.Requests;

public interface RequestsInterface {

    void generateRequest(String userId, String ibn);

    int getRequestByUserId(String userId);

    int getRequestIndexByIbnNumber(String ibn);

    Requests getRequest(int requestIndex);

    void approveOrDeny(int requestIndex, char approveOrDenied);

    void removeRequest(int requestIndex);
}
