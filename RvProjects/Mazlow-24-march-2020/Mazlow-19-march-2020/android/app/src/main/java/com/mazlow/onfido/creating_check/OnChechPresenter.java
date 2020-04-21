package com.mazlow.onfido.creating_check;

import org.json.JSONArray;
import org.json.JSONObject;

public interface OnChechPresenter {

    void doCheck(String token,String application_id,String type, JSONObject report);
}
