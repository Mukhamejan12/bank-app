package Data;

import java.io.Serializable;

public class Packet implements Serializable {
    private Serializable data;
    private String code;

    public Packet() {}
    public Packet(String code) {
        this.code = code;
    }
    public Packet(String code, Serializable data) {
        this.data = data;
        this.code = code;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}