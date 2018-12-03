package nz.frequency.timesheet.util;

import com.google.gson.annotations.SerializedName;

public class TokenServerResponse {


    @SerializedName("jwt_token")
    private String jwtToken;


    public TokenServerResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
