package com.onlineapp.libraryapp.model;

import java.util.Map;

public class Result {
    private Map<String, String> addElementResultSuccessAdded;

    private Map<String, String> addElementResultNotAdded;

    private Map<String, String> removeElementResultSuccessRemoved;

    private Map<String, String> removeElementResultNotRemoved;

    private Map<String, String> modifyElementResultSuccessModified;

    private Map<String, String> modifyElementResultNotModified;

    private Map<String, String> hasReferences;


    public Result(Map<String, String> addElementResultSuccessAdded, Map<String, String> addElementResultNotAdded, Map<String, String> removeElementResultSuccessRemoved, Map<String, String> removeElementResultNotRemoved, Map<String, String> modifyElementResultSuccessModified, Map<String, String> modifyElementResultNotModified, Map<String, String> hasReferences) {
        this.addElementResultSuccessAdded = addElementResultSuccessAdded;
        this.addElementResultNotAdded = addElementResultNotAdded;
        this.removeElementResultSuccessRemoved = removeElementResultSuccessRemoved;
        this.removeElementResultNotRemoved = removeElementResultNotRemoved;
        this.modifyElementResultSuccessModified = modifyElementResultSuccessModified;
        this.modifyElementResultNotModified = modifyElementResultNotModified;
        this.hasReferences = hasReferences;
    }


    public Map<String, String> getAddElementResultSuccessAdded() {
        return addElementResultSuccessAdded;
    }

    public void setAddElementResultSuccessAdded(Map<String, String> addElementResultSuccessAdded) {
        this.addElementResultSuccessAdded = addElementResultSuccessAdded;
    }

    public Map<String, String> getAddElementResultNotAdded() {
        return addElementResultNotAdded;
    }

    public void setAddElementResultNotAdded(Map<String, String> addElementResultNotAdded) {
        this.addElementResultNotAdded = addElementResultNotAdded;
    }

    public Map<String, String> getRemoveElementResultSuccessRemoved() {
        return removeElementResultSuccessRemoved;
    }

    public void setRemoveElementResultSuccessRemoved(Map<String, String> removeElementResultSuccessRemoved) {
        this.removeElementResultSuccessRemoved = removeElementResultSuccessRemoved;
    }

    public Map<String, String> getRemoveElementResultNotRemoved() {
        return removeElementResultNotRemoved;
    }

    public void setRemoveElementResultNotRemoved(Map<String, String> removeElementResultNotRemoved) {
        this.removeElementResultNotRemoved = removeElementResultNotRemoved;
    }

    public Map<String, String> getModifyElementResultSuccessModified() {
        return modifyElementResultSuccessModified;
    }

    public void setModifyElementResultSuccessModified(Map<String, String> modifyElementResultSuccessModified) {
        this.modifyElementResultSuccessModified = modifyElementResultSuccessModified;
    }

    public Map<String, String> getModifyElementResultNotModified() {
        return modifyElementResultNotModified;
    }

    public void setModifyElementResultNotModified(Map<String, String> modifyElementResultNotModified) {
        this.modifyElementResultNotModified = modifyElementResultNotModified;
    }

    public Map<String, String> getHasReferences() {
        return hasReferences;
    }

    public void setHasReferences(Map<String, String> hasReferences) {
        this.hasReferences = hasReferences;
    }

    @Override
    public String toString() {
        return "Result{" +
                "addElementResultSuccessAdded=" + addElementResultSuccessAdded +
                ", addElementResultNotAdded=" + addElementResultNotAdded +
                ", removeElementResultSuccessRemoved=" + removeElementResultSuccessRemoved +
                ", removeElementResultNotRemoved=" + removeElementResultNotRemoved +
                ", modifyElementResultSuccessModified=" + modifyElementResultSuccessModified +
                ", modifyElementResultNotModified=" + modifyElementResultNotModified +
                '}';
    }
}
