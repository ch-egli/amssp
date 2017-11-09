package ch.sbb.apim.amssp.model;

/**
 * TODO: Describe
 *
 * @author Christian Egli
 * @since 11/8/17.
 */
public class StringKeyValue {

    private String key;
    private String value;

    public StringKeyValue() {
    }

    public StringKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
