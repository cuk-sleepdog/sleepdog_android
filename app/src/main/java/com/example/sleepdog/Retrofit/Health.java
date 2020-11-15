package com.example.sleepdog.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Health {
    /**
     * {
     * "Product": "value", // 목밴드ID 생성 시 자동으로 1씩 증가하는 id값
     * "DATE": "value", // 생성날짜
     * "Time": "value", // 생성시간
     * "Bpm": "value", // 심박수
     * "Temp": "value", // 체온
     * "CHK": "value" // 수면상태 체크
     * }
     */
    @SerializedName("CHK")
    String CHK;

    @SerializedName("Bpm")
    String Bpm;

    @SerializedName("Temp")
    String Temp;

    public String getCHK() {
        return CHK;
    }

    public String getBpm() {
        return Bpm;
    }

    public String getTemp() {
        return Temp;
    }

}
