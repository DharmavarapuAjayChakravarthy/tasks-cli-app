import java.util.Scanner;

public class Main {
    private static AddContract.AddInputContract createInputContractForAdd (){
        Scanner sc = new Scanner(System.in);
        Todo todo = new Todo();
        AddContract.AddInputContract addInputContract = new AddContract.AddInputContract();
        System.out.println("Enter ID");
        addInputContract.id= sc.nextInt();
        todo.id=addInputContract.id;
        System.out.println("Enter Name");
        addInputContract.name=sc.next();
        todo.name=addInputContract.name;
        System.out.println("Enter Date(dd/mm/yyyy)");
        addInputContract.date=sc.next();
        todo.date=addInputContract.date;
        System.out.println("Enter Status");
        addInputContract.status=Status.valueOf(sc.next().trim());
        todo.status = addInputContract.status;
        return addInputContract;
    }

    private static DeleteContract.DeleteInputContract createInputContractForDelete(){
        Scanner sc = new Scanner(System.in);
        Todo todo = new Todo();
        DeleteContract.DeleteInputContract deleteInputContract = new DeleteContract.DeleteInputContract();
        System.out.println("Enter the id you want to delete");
        deleteInputContract.id=sc.nextInt();
        todo.id=deleteInputContract.id;
        return deleteInputContract;
    }
    private static ModifyContract.ModifyInputContract createInputContractForModify(){
        Scanner sc = new Scanner(System.in);
        ModifyContract.ModifyInputContract modifyInputContract = new ModifyContract.ModifyInputContract();
        Todo todo = new Todo();
        System.out.println("Enter the id you want to update");
        modifyInputContract.id=sc.nextInt();
        todo.id=modifyInputContract.id;
        System.out.println("Enter the status to be updated");
        modifyInputContract.status=Status.valueOf(sc.next().trim());
        todo.status=modifyInputContract.status;
        return modifyInputContract;
    }
    private static GetContract.GetInputContract createInputContractForGetAll(){
        GetContract.GetInputContract getInputContract = new GetContract.GetInputContract();
        Scanner sc = new Scanner(System.in);
        System.out.println("List of all the Items");
        return getInputContract;
    }

    public static void main(String[] args) {

        TaskRepository repo = new TaskRepository();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the operation you want to perform DBTodo or FileTodo or HashMapTodo");
        String input = sc.next();
        TaskServices taskServices = repo.todoOperation(input);
        int choice;
        while (true){
            System.out.println("Enter your choice 1.Insert a record" +
                    " 2.Remove a record 3.Update a record " +
                    "4.Get all records 0.exit");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    taskServices.add(createInputContractForAdd());
                    break;
                case 2:
                    taskServices.delete(createInputContractForDelete());
                    break;
                case 3:
                    taskServices.modify(createInputContractForModify());
                    break;
                case 4:
                    taskServices.show(createInputContractForGetAll());
                    break;
                case 0:
                    return;
            }
        }

    }
}
