package sql.logic.models.entities;

import sql.logic.models.util.DataTransferObject;

import java.util.Date;

public class LogStatement implements DataTransferObject {
    private long id;
    private long id_approver;
    private long id_employee;
    private String comment_ls;
    private int days_sum;
    private int type_leave;
    private int approve;
    private Date date_leave;
    private Date date_of_ls;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdApprover() {
        return id_approver;
    }

    public void setIdApprover(long id_approver) {
        this.id_approver = id_approver;
    }

    public long getIdEmployee() {
        return id_employee;
    }

    public void setIdEmployee(long id_employee) {
        this.id_employee = id_employee;
    }

    public String getCommentLs() {return comment_ls;}

    public void setCommentLs(String comment_ls) {
        this.comment_ls = comment_ls;
    }

    public int getDaysSum() {return days_sum;}

    public void setDaysSum(int days_sum) {
        this.days_sum = days_sum;
    }

    public int getTypeLeave() {return type_leave;}

    public void setTypeLeave(int type_leave) {
        this.type_leave = type_leave;
    }

    public int getApprove() {return approve;}

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public Date getDateLeave() {
        return date_leave;
    }

    public void setDateLeave(Date date_leave) {
        this.date_leave = date_leave;
    }

    public Date getDateOfLs() {
        return date_of_ls;
    }

    public void setDateOfLs(Date date_of_ls) {
        this.date_of_ls = date_of_ls;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("log_statement{");
        sb.append("id=").append(id);
        sb.append(", id_approver='").append(id_approver).append('\'');
        sb.append(", id_employee='").append(id_employee).append('\'');
        sb.append(", comment_ls='").append(comment_ls).append('\'');
        sb.append(", days_sum='").append(days_sum).append('\'');
        sb.append(", type_leave='").append(type_leave).append('\'');
        sb.append(", approve='").append(approve).append('\'');
        sb.append(", date_leave='").append(date_leave).append('\'');
        sb.append(", date_of_ls='").append(date_of_ls).append('\'');
        sb.append('}');
        return sb.toString();
    }
}