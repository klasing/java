package src;
//****************************************************************************
//*                     import
//****************************************************************************
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
//****************************************************************************
//*                     MyObject
//****************************************************************************
public class MyObject {
    public MyObject() {
        System.out.println("<<constructor>> MyObject()");
    }

    @JsonSetter("cook")
    public void setCook(String _cook) {
        this.cook = _cook;
    }
    @JsonGetter("cook")
    public String getCook() { return cook; }

    @JsonSetter("lock")
    public void setLock(boolean _lock) {
        this.lock = _lock;
    }
    @JsonGetter("lock")
    public boolean getLock() { return lock; }

    @JsonSetter("eating")
    public void setEating(ArrayList<String> _listEating) {
        for (String strElement : _listEating) {
            this.listEating.add(strElement);
        }
    }
    @JsonGetter("eating")
    public List<String> getEating() { return listEating; }

    @Override
    public String toString() {
        String strReturn = cook + " " + lock;
        Iterator<String> iteElement = listEating.iterator();
        while (iteElement.hasNext()) {
            strReturn += " " + iteElement.next();
        }
        return strReturn;
    }

    @JsonIgnore
    public int getListEatingSize() { return listEating.size(); }
    @JsonIgnore
    public void setEating(String strEating) { listEating.add(strEating); }
    @JsonIgnore
    public void setEatingAt(int iIdx, String strEating) {
        listEating.set(iIdx, strEating);
    }
    @JsonIgnore
    public String getEatingAt(int iIdx) {
        return listEating.get(iIdx);
    }
    @JsonIgnore
    public void removeEating(int iIdx) {
        listEating.remove(iIdx);
    }
    //************************************************************************
    //*                 declare
    //************************************************************************
    private String cook;
    private boolean lock;
    private List<String> listEating = new ArrayList<String>();
}