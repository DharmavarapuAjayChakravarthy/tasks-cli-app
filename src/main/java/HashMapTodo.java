import java.util.*;

public  class HashMapTodo implements TaskServices {
    HashMap<Integer, Todo> todoMap = new HashMap<>();
    Todo todo = new Todo();
    Scanner sc = new Scanner(System.in);
    @Override
    public void add() {
        System.out.println("Add a task (Id, name,date(dd/mm/yyyy)," +
                " status)");
        todo.id = sc.nextInt(); //taking id as input
        todo.name = sc.next(); //name input
        todo.date = sc.next(); //date input
        todo.status = Status.valueOf(sc.next()); //status input
        todoMap.put(todo.id, todo); //storing in hashmap(in memory)

    }

    @Override
    public void delete() {
        System.out.println("Enter id to remove task");
        int remove_id = sc.nextInt(); //taking id of the record to remove
        todoMap.remove(remove_id);

    }

    @Override
    public void modify() {
        System.out.println("Enter id to update a task");
        int update_id = sc.nextInt(); //taking id to be updated
        String status_update = sc.nextLine().trim();
        /*trimming the user
         *input
         */
        Todo existingTask = todoMap.get(update_id);
        if (null == existingTask) {
            System.out.println("invalid ID");
        }
        Objects.requireNonNull(existingTask).
                setStatus(Status.valueOf(status_update));
        /*updating the
         *status
         */
        System.out.print(existingTask.toString());

    }

    @Override
    public void show() {
        System.out.println("List all tasks");
        TreeMap<Integer, Todo> sorted = new TreeMap<>(todoMap);
        Set<Integer> keys = sorted.keySet();
            for (Integer key : keys) {
                System.out.print(key + " " + sorted.get(key).toString()+ "\n");
            }
        }
    }
