package de.ginisolutions.trader.domain.model.account;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

/**
 * The UserAccount entity.\n@author A true hipster
 */
@Document(collection = "user_account")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("field_name")
    private String fieldName;

    @DBRef
    @Field("keyCollection")
    private KeyCollection keyCollection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public UserAccount fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public KeyCollection getKeyCollection() {
        return keyCollection;
    }

    public UserAccount keyCollection(KeyCollection keyCollection) {
        this.keyCollection = keyCollection;
        return this;
    }

    public void setKeyCollection(KeyCollection keyCollection) {
        this.keyCollection = keyCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAccount)) {
            return false;
        }
        return id != null && id.equals(((UserAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + getId() +
                ", fieldName='" + getFieldName() + "'" +
                "}";
    }
}
