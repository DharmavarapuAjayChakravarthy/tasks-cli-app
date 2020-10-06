import java.util.*;

public  class HashMapTodo implements TaskServices {
    HashMap<Integer, Todo> todoMap = new HashMap<>();
    Todo todo = new Todo();
    Scanner sc = new Scanner(System.in);
    @Override
    public AddContract.AddOutputContract add(AddContract.AddInputContract addInputContract) {
        AddContract.AddOutputContract addOutputContract = new AddContract.AddOutputContract();
        todo.id= addInputContract.id;
        todo.name = addInputContract.name;
        todo.date = addInputContract.date;
        todo.status= addInputContract.status;
        todoMap.put(addInputContract.id,todo); //storing in hashmap(in memory)
        addOutputContract.id= todo.id;
        addOutputContract.name=todo.name;
        addOutputContract.date=todo.date;
        addOutputContract.status=todo.status;
        return addOutputContract;
    }


    @Override
    public DeleteContract.DeleteOutputContract delete(DeleteContract.DeleteInputContract deleteInputContract) {
        DeleteContract.DeleteOutputContract deleteOutputContract = new DeleteContract.DeleteOutputContract();
        deleteInputContract.id= todo.id;
        deleteOutputContract.id=deleteInputContract.id;
        todoMap.remove(deleteInputContract.id);
        return deleteOutputContract;
    }

    @Override
    public ModifyContract.ModifyOutputContract modify(ModifyContract.ModifyInputContract modifyInputContract) {
        Todo existingTask = todoMap.get(modifyInputContract.id);
        ModifyContract.ModifyOutputContract modifyOutputContract = new ModifyContract.ModifyOutputContract();
        (existingTask).setStatus(modifyInputContract.status);
        System.out.println(modifyInputContract.status);
        modifyOutputContract.status=existingTask.status;
        (existingTask).setStatus(modifyOutputContract.status);
        return modifyOutputContract;
    }

    @Override
    public GetContract.GetOutputContract show(GetContract.GetInputContract getInputContract) {
        GetContract.GetOutputContract getOutputContract = new GetContract.GetOutputContract();
        TreeMap<Integer,Todo> sorted = new TreeMap<>(todoMap);
        Set<Integer> keys = sorted.keySet();
            for (Integer key : keys) {
                System.out.print(key + " " + sorted.get(key).toString()+ "\n");
            }
            getOutputContract.id = todo.id;
            getOutputContract.name = todo.name;
            getOutputContract.date = todo.date;
            getOutputContract.status=todo.status;
            return getOutputContract;
        }
    }
