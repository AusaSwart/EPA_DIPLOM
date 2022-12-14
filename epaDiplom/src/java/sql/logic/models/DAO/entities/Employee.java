package java.sql.logic.models.DAO.entities;

import java.sql.logic.models.DAO.entities.utilDTO.DataTransferObject;

import java.util.List;

public class Employee implements DataTransferObject {
    private long id;
    private int privilege;
    private long id_dep;
    private List<Long> employeeIDs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }


    public long getIdDep() {
        return id_dep;
    }

    public void setIdDep(long id_dep) {
        this.id_dep = id_dep;
    }
    public List<Long> getEmployeeIDs (){ return employeeIDs; }
    public void setEmployeeIDs(List<Long> approves){ this.employeeIDs = approves;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("___Employee №");
        sb.append(" ").append(id);
        sb.append("\n Privilege ").append(privilege);
        sb.append("\n Id of department ").append(id_dep);
        sb.append("\n ");
        return sb.toString();
    }
}
