import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskRepository repo = new TaskRepository();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the operation you want to perform DBTodo or FileTodo or HashMapTodo");
        String input = sc.next();
        TaskServices todo = repo.todoOperation(input);
        int choice;
        while (true){
            System.out.println("Enter your choice 1.Insert a record" +
                    " 2.Remove a record 3.Update a record " +
                    "4.Get all records 0.exit");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    todo.add();
                    break;
                case 2:
                    todo.delete();
                    break;
                case 3:
                    todo.modify();
                    break;
                case 4:
                    todo.show();
                    break;
                case 0:
                    return;
            }
        }

    }
}
