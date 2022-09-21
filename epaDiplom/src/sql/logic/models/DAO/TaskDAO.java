package sql.logic.models.DAO;

import sql.logic.models.entities.EmployeeTask;
import sql.logic.models.entities.Task;
import sql.logic.models.util.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO extends DataAccessObject<Task> {

    public TaskDAO(Connection connection) {
        super(connection);
    }
    private static final String GET_ONE = "SELECT id, date_task FROM task WHERE id = ?";
    private static final String DELETE = "DELETE FROM task WHERE id = ?";
    private static final String UPDATE = "UPDATE task SET date_task = ? WHERE id = ?";
    private static final String INSERT = "INSERT INTO task (date_task) VALUES (?)";

    public List<Task> findByIDListOfTask(long id){
        List<Task> tasks = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setDateTask(rs.getDate("date_task"));
                tasks.add(task);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public Task findById(long id) {
        Task task = new Task();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                task.setId(rs.getLong("id"));
                task.setDateTask(rs.getDate("date_task"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task update(Task dto) {
        Task task = null;
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            statement.setDate(1, (Date) dto.getDateTask());
            statement.setLong(2, dto.getId());
            statement.execute();
            this.connection.commit();
            task = this.findById(dto.getId());
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
        return task;
    }


    @Override
    public Task create(Task dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            statement.setDate(1, (Date) dto.getDateTask());
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
