package com.itheima.mm.domain.system;

public class Dept {
    private String id;
    private String deptName;
    private String parentId;
    private Integer state;
    
    private Dept parent;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDeptName() {
        return deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public Integer getState() {
        return state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    public Dept getParent() {
        return parent;
    }
    
    public void setParent(Dept parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return "Dept{" +
                "id='" + id + '\'' +
                ", deptName='" + deptName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", state=" + state +
                ", parent=" + parent +
                '}';
    }
}
