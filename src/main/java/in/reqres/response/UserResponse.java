package in.reqres.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private UserDetail data;
    private Support support;

    // Getters and setters
    public UserDetail getData() { return data; }
    public void setData(UserDetail data) { this.data = data; }

    public Support getSupport() { return support; }
    public void setSupport(Support support) { this.support = support; }
}
