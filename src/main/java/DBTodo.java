import java.sql.*;
import java.util.Scanner;

public  class DBTodo implements  TaskServices {
    Connection c = null;
    @Override
    public void add() {
        try {

            Statement stmt ;
            Class.forName("org.postgres.Driver");
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
        System.out.println("Add a task (Id, name,date(dd/mm/yyyy)," +
                " status)");
        Scanner ip = new Scanner(System.in);
        Todo todo = new Todo();
        System.out.println("Enter ID"); //user id
        todo.id = ip.nextInt();
        System.out.println("Enter Name"); //user name
        todo.name = ip.next();
        System.out.println("Enter Date"); //date
        todo.date = ip.next();
        System.out.println("Enter Status"); // status of the work
        todo.status = Status.valueOf(ip.next());
        System.out.println(todo.toString());
        try {
            Connection c ;
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb",
                    "postgres", "root");
            PreparedStatement st = c.prepareStatement("INSERT INTO" +
                    " todo (id, name, date, status)" +
                    " VALUES (?, ?, ?, ?)"); // inserting records
            st.setInt(1, todo.id);
            st.setString(2, todo.name);
            st.setString(3, todo.date);
            st.setString(4, String.valueOf(todo.status));
            st.executeUpdate();
            System.out.println("Inserted records successfully");
            } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void delete() {
        try {
            Class.forName("org.postgres.Driver");
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb",
                    "postgres", "root");
        }catch (Exception e) {
                System.out.println("table cannot be created");
            }
        System.out.println("Enter the id to remove the task: ");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID");
        int remove_id = input.nextInt();
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM" +
                    " todo WHERE id = ?");
            /* delete on id */
            st.setInt(1, remove_id);
            st.executeUpdate();
            System.out.println("Deleted Successfully");
        } catch (Exception exp) {
            System.out.println("cannot delete");
        }

    }


    @Override
    public void modify() {
        try {
            Class.forName("org.postgres.Driver");
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb",
                    "postgres", "root");
        }catch (Exception e) {
            System.out.println("table cannot be created");
        }
        System.out.println("Enter the ID of the task you " +
                "want to Update: ");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID");
        int update_id = input.nextInt();
        System.out.println("Enter Status to be Updated");
        String update_status = input.next();
        try {
            PreparedStatement st = c.prepareStatement("UPDATE todo" +
                    " set status = ? where ID=?;");
            /* updating records */
            st.setString(1, update_status);
            st.setInt(2, update_id);
            st.executeUpdate();
            System.out.println("Updated Successfully");
        } catch (Exception exp) {
            System.out.println("cannot update");
        }

    }

    @Override
    public void show() {
        try {
            Class.forName("org.postgres.Driver");
            c = DriverManager.getConnection("jdbc:postgres:" +
                            "//localhost:" +
                            "5432" +
                            "/mydb",
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
    }
}
