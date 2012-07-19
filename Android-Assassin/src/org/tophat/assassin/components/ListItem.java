package org.tophat.assassin.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListItem implements Map<String, String> {
    public String title;
    public String subTitle;
    public String type;
    
    // default constructor
    public ListItem() {
            this("Title", "Subtitle", null);
    }
    
    // main constructor
    public ListItem(String title, String subTitle, String type) {
            super();
            this.title = title;
            this.subTitle = subTitle;
            this.type = type;
    }
    
    // String representation
    public String toString() {
            return this.title + " : " + this.subTitle;
    }
    
    // Map interface classes
    
    // return a count of our members
    public int size() {
            return 2;
    }
    
    // set the values of the object to null
    public void clear() {
            this.title = null;
            this.subTitle = null;
            this.type = null;
    }
    
    // return all of the values as a collection
    public ArrayList<String> values() {
            ArrayList<String> list = new ArrayList<String>();
            
            list.add(title);
            list.add(subTitle);
            
            return list;
    }
    
    // if the values of the members are null, return true
    public boolean isEmpty() {
            if ((this.title == null) && (this.subTitle == null) && (this.type == null)) {
                    return true;
            } else {
                    return false;
            }
    }
    
    // return a set of the members
    public Set<String> keySet() {
            Set<String> s = new HashSet<String>();
            
            s.add("title");
            s.add("subTitle");
            
            return s;
    }
    
    // return a set of the member values
    public Set entrySet() {
            Set<String> s = new HashSet<String>();
            
            s.add(this.title);
            s.add(this.subTitle);
            
            return s;
    }
    
    // return the value of the given key
    public String get(Object key) {
            if (key.equals("title")) {
                    return this.title;
            }
            if (key.equals("subTitle")) {
                    return this.subTitle;
            }
            if(key.equals("type"))
            {
            	return this.type;
            }
            // if we can't return a value, throw the exception
            throw new ClassCastException();
    }
    
    // set the value of a given key
    public String put(String key, String value) {
            if (key.equals("title")) {
                    this.title = value;
            }
            if (key.equals("subTitle")) {
                    this.subTitle = value;
            }
            if(key.equals("type"))
            {
            	this.type = value;
            }
            return value;
    }
    
    // remove a key (nullify)
    public String remove(Object key) {
            String value = null;
            if (key.equals("title")) {
                    value = this.title;
                    this.title = null;
            }
            if (key.equals("subTitle")) {
                    value = this.subTitle;
                    this.subTitle = null;
            }
            return value;
    }
    
    // return boolean if we have a member
    public boolean containsKey(Object key) {
            if (key.equals("title")) {
                    return true;
            }
            if (key.equals("subTitle")) {
                    return true;
            }
            if(key.equals("type"))
            {
            	return true;
            }
            return false;
    }
    
    // return boolean if we have a member's value
    public boolean containsValue(Object value) {
            if (value.equals(this.title)) {
                    return true;
            }
            if (value.equals(this.subTitle)) {
                    return true;
            }
            if(value.equals(this.type))
            {
            	return true;
            }
            return false;
    }

    // set the values of this map to that of another
    public void putAll(Map<? extends String, ? extends String> arg0) {
            // we only need the stub.
    }
    
}