public class TaskRepository  {
    public TaskServices  todoOperation (String operation){
        if(operation == null){
            return null;
        }
        if(operation.equalsIgnoreCase("DBTodo")){
         return (TaskServices) new DBTodo();

        } else if(operation.equalsIgnoreCase("FileTodo")){
          return (TaskServices) new FileTodo();

        } else if(operation.equalsIgnoreCase("HashMapTodo")){
          return (TaskServices) new HashMapTodo();
        }
        return null;
    }
}
