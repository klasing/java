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
//*                     JsonObject
//****************************************************************************
public class JsonObject {
    public JsonObject() {
        System.out.println("<<constructor>> JsonObject()");
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
        listEating.clear();
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

    //************************************************************************
    //*                 declare
    //************************************************************************
    private String cook;
    private boolean lock;
    private List<String> listEating = new ArrayList<String>();

}