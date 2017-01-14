/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.model;

/**
 *
 * @author Chintan Kanal
 */
public class FieldValue implements Comparable<FieldValue> {
    private Field field;
    private String value;

    public FieldValue(Field field, String value) {
        this.field = field;
        this.value = value;
    }

    public Field getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return this.field == ((FieldValue) obj).getField();
    }

    @Override
    public int hashCode() {
        return field.name().hashCode() + value.hashCode();
    }
    
    public int compareTo(FieldValue fieldValue) {
        return (this.field.getDisplaySequence() < fieldValue.field.getDisplaySequence() ) ? -1: (this.field.getDisplaySequence() > fieldValue.field.getDisplaySequence()) ? 1:0;
    }

    @Override
    public String toString() {
        return field.name() + ":" + value;
    }
    
}
