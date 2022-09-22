package sql.logic.models.DAO;

import sql.logic.models.entities.JobEmployee;
import sql.logic.models.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JobEmployeeDAO extends DataAccessObject<JobEmployee> {

    public JobEmployeeDAO(Connection connection) {
        super(connection);
    }
    private static final String GET_ONE = "SELECT id_employee, id_job_title FROM job_employee WHERE id_employee=?";
    private static final String DELETE = "DELETE FROM job_employee WHERE id_employee = ?";
    private static final String UPDATE = "UPDATE job_employee SET id_job_title = ? WHERE id_employee = ?";
    private static final String INSERT = "INSERT INTO job_employee (id_job_title) VALUES (?)";

    @Override
    public JobEmployee findById(long id) {
        JobEmployee jobEmployee = new JobEmployee();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                jobEmployee.setId(rs.getLong("id_employee"));
                jobEmployee.setIdJobTitle(rs.getLong("id_job_title"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return jobEmployee;
    }

    @Override
    public List<JobEmployee> findAll() {
        return null;
    }

    @Override
    public JobEmployee update(JobEmployee dto) {
        JobEmployee jobEmployee = null;
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setLong(2, dto.getIdJobTitle());
            statement.setLong(2, dto.getId());
            statement.execute();
            this.connection.commit();
            jobEmployee = this.findById(dto.getId());
        }catch(SQLException e){
            try{
                this.connection.rollback();
            }catch (SQLException sqle){
                e.printStackTrace();
                throw new RuntimeException(sqle);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return jobEmployee;
    }


    @Override
    public JobEmployee create(JobEmployee dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setLong(1, dto.getIdJobTitle());
            statement.execute();
            int id = this.getLastVal(EMPLOYEE_SEQUENCE);
            return this.findById(id);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE);){
            statement.setLong(1, id);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}