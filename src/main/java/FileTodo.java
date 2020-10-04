import java.io.*;
import java.util.Scanner;


public  class FileTodo implements TaskServices  {
    private static final String newLine = System.getProperty("line.separator");
    Scanner sc = new Scanner(System.in);
    String fileName = "todo.txt";


    @Override
    public void add()  {
        try{
        System.out.println("Add a task (Id, name,date(dd/mm/yyyy)," +
                " status)");
        Scanner ip = new Scanner(System.in);
        Todo todo = new Todo();
        int id = ip.nextInt();
        todo.name = ip.next();
        todo.date = ip.next();
        todo.status = Status.valueOf(ip.next());
        String note = id + "\t" + todo.name + "\t" +
                todo.date + "\t" + todo.status;
        PrintWriter myObj;
        myObj = new PrintWriter(new FileOutputStream(fileName, true));
        myObj.write(note + newLine); //writing into text file
        myObj.close(); // closing
    }catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void delete() {
        try {
            System.out.println("Enter the id to remove the task: ");
            String remove_id = sc.next(); //taking ID to remove
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = reader.readLine()) != null) //reading text in file
            {
                if (!line.contains(remove_id)) // if line has that ID
                {
                    text.append(line).append("\n");
                }
            }
            FileWriter remove = new FileWriter(fileName);
            remove.write(text.toString());
            remove.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void modify() {
        try {
            System.out.println("Enter the ID of the task you" +
                    " want to Update: ");

        String find_id = sc.next();
        String s;
        String[] words;
        StringBuilder oldText = new StringBuilder();
        BufferedReader br_update = new BufferedReader(new FileReader(fileName));
        while ((s = br_update.readLine()) != null) {
            words = s.split("\t");
            System.out.println(words[3]); //splitting on word length
            if (words[0].equals(find_id)) {
                System.out.println("Update the status");
                sc.nextLine();
                String new_status = sc.nextLine();
                String replaced = s.replaceAll(words[3],
                        new_status); //replacing that word with new
                oldText.append(replaced).append("\n");
                System.out.println(oldText);
                break;
            } else {
                oldText.append(s).append("\n");
            }
        }
        FileWriter writer = new FileWriter(fileName);
        writer.write(oldText.toString());
        writer.close();
    }catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void show() {
        try{
        System.out.print("List of all the tasks\n");
        BufferedReader br_list = new BufferedReader(new FileReader(fileName));
        String st;
        while ((st = br_list.readLine()) != null)
            /*reading from
             *text file
             */
            System.out.println(st);
        /* printing all the records
         *in the text file
         */
    }catch (IOException e){
            System.out.println(e);
        }
    }
}