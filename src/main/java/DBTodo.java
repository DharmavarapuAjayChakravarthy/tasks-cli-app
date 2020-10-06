import java.sql.*;
import java.util.Scanner;

public  class DBTodo implements  TaskServices {
    Connection c = null;
    TaskServices taskServices;
    @Override
    public AddContract.AddOutputContract add(AddContract.AddInputContract addInputContract) {
        AddContract.AddOutputContract addOutputContract = new AddContract.AddOutputContract();
        try {

            Statement stmt ;
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    System.err.println(e);
                }
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb",
                    "postgres", "root");
            System.out.println("DB successfully Connected");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Todo " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " DATE           TEXT     NOT NULL, " +
                    " STATUS         TEXT     NOT NULL  )";
            //Create table if not exists
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.out.println("table cannot be created");
        }
        System.out.println("Table Created Successfully");

        try {
            Connection c ;
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb?useSSL=false",
                    "postgres", "root");
            PreparedStatement st = c.prepareStatement("INSERT INTO" +
                    " todo (id, name, date, status)" +
                    " VALUES (?, ?, ?, ?)"); // inserting records
            st.setInt(1, addInputContract.id);
            st.setString(2, addInputContract.name);
            st.setString(3, addInputContract.date);
            st.setString(4, String.valueOf(addInputContract.status));
            st.executeUpdate();
            addOutputContract.id=addInputContract.id;
            addOutputContract.name = addInputContract.name;
            addOutputContract.date = addInputContract.date;
            addOutputContract.status = addInputContract.status;
            System.out.println("Inserted records successfully");
            } catch (Exception ex) {
            System.out.println(ex);
        }
            return addOutputContract;

    }

    @Override
    public DeleteContract.DeleteOutputContract delete(DeleteContract.DeleteInputContract deleteInputContract) {
        DeleteContract.DeleteOutputContract deleteOutputContract = new DeleteContract.DeleteOutputContract();
        try {
            Class.forName("org.postgres.Driver");
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb?useSSL=false",
                    "postgres", "root");
        }catch (Exception e) {
                System.out.println("table cannot be created");
            }
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM" +
                    " todo WHERE id = ?");
            /* delete on id */
            st.setInt(1,deleteInputContract.id);
            st.executeUpdate();
            System.out.println("Deleted Successfully");
        } catch (Exception exp) {
            System.out.println("cannot delete");
        }
        return deleteOutputContract;
    }


    @Override
    public ModifyContract.ModifyOutputContract modify(ModifyContract.ModifyInputContract modifyInputContract) {
        ModifyContract.ModifyOutputContract modifyOutputContract = new ModifyContract.ModifyOutputContract();
        try {
            Class.forName("org.postgres.Driver");
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb?useSSL=false",
                    "postgres", "root");
        }catch (Exception e) {
            System.out.println("table cannot be created");
        }
        try {
            PreparedStatement st = c.prepareStatement("UPDATE todo" +
                    " set status = ? where ID=?;");
            /* updating records */
            st.setString(1, String.valueOf(modifyInputContract.status));
            st.setInt(2, modifyInputContract.id);
            st.executeUpdate();
            System.out.println("Updated Successfully");
        } catch (Exception exp) {
            System.out.println("cannot update");
        }
        return modifyOutputContract;
    }

    @Override
    public GetContract.GetOutputContract show(GetContract.GetInputContract getInputContract) {
        GetContract.GetOutputContract getOutputContract = new GetContract.GetOutputContract();
        try {
            Class.forName("org.postgres.Driver");
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb?useSSL=false",
                    "postgres", "root");
        }catch (Exception e) {
            System.out.println("table cannot be created");
        }
        System.out.print("List of all the tasks\n");
        try {
            Statement stmt;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM" +
                    " todo ORDER BY id ASC;");
            /*getting all the details of the user in db */
            while (rs.next()) {
                int uid = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String status = rs.getString("status");
                System.out.println("ID:" + uid + ", NAME = " +
                        name + ", Date = " +
                        date + ", STATUS = " + status);
                /* assigning keys with values */
            }
        } catch (Exception exp) {
            System.out.println("cannot get details");
        }
        return getOutputContract;
    }
}
