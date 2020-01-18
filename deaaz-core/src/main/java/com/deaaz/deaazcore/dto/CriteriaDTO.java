package com.deaaz.deaazcore.dto;

import com.deaaz.deaazcore.refs.OperatorType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriteriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Object> values;
    private String field;
    private OperatorType operator;

    public CriteriaDTO(Object value, String field, OperatorType operator) {
        getValues().add(value);
        this.field = field;
        this.operator = operator;
    }


    public CriteriaDTO(List<Object> values, String field, OperatorType operator) {
        getValues().addAll(values);
        this.field = field;
        this.operator = operator;
    }

    public CriteriaDTO() {
    }

    public List<Object> getValues() {
        if(this.values == null){
            this.values = new ArrayList<Object>();
        }
        return this.values;
    }

    public List<Object> setValues() {
        this.values = new ArrayList<Object>();
        return this.values;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OperatorType getOperator() {
        return this.operator;
    }

    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }
}
