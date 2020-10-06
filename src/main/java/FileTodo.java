import java.io.*;
import java.util.Scanner;


public  class FileTodo implements TaskServices  {
    private static final String newLine = System.getProperty("line.separator");
    Scanner sc = new Scanner(System.in);
    String fileName = "todo.txt";


    @Override
    public AddContract.AddOutputContract add(AddContract.AddInputContract addInputContract)  {
        AddContract.AddOutputContract addOutputContract = new AddContract.AddOutputContract();
        try{
        String note = addInputContract.id + "\t" + addInputContract.name + "\t" +
                addInputContract.date + "\t" + addInputContract.status;
        PrintWriter myObj;
        myObj = new PrintWriter(new FileOutputStream(fileName, true));
        myObj.write(note + newLine); //writing into text file
            addOutputContract.id=addInputContract.id;
            addOutputContract.name=addInputContract.name;
            addOutputContract.date=addInputContract.date;
            addOutputContract.status = addInputContract.status;
        myObj.close(); // closing
    }catch (IOException e){
            System.out.println(e);
        }
        return addOutputContract;
    }

    @Override
    public DeleteContract.DeleteOutputContract delete(DeleteContract.DeleteInputContract deleteInputContract) {
        try {
            DeleteContract.DeleteOutputContract deleteOutputContract = new DeleteContract.DeleteOutputContract();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = reader.readLine()) != null) //reading text in file
            {
                if (!line.contains(String.valueOf(deleteInputContract.id))) // if line has that ID
                    text.append(line).append("\n");
            }
            FileWriter remove = new FileWriter(fileName);
            remove.write(text.toString());
            remove.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ModifyContract.ModifyOutputContract modify(ModifyContract.ModifyInputContract modifyInputContract) {
        ModifyContract.ModifyOutputContract modifyOutputContract = new ModifyContract.ModifyOutputContract();
        try {
            String find_id = sc.next();
            String s;
            String[] words = null;
            String oldText = "";
            BufferedReader br_update = new BufferedReader(new FileReader(fileName));
            while ((s = br_update.readLine()) != null) {
                words = s.split("\t");
                System.out.println(words[3]); //splitting on word length
                if (words[0].equals(find_id)) {
                    modifyOutputContract.status=modifyInputContract.status;
                    String replaced = s.replaceAll(words[3],
                            modifyOutputContract.status.toString()); //replacing that word with new
                    oldText += replaced + "\n";
                    System.out.println(oldText);
                    break;
                } else {
                    oldText += s + "\n";
                }
            }
            FileWriter writer = new FileWriter(fileName);
            writer.write(oldText);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modifyOutputContract;
    }

    @Override
    public GetContract.GetOutputContract show(GetContract.GetInputContract getInputContract) {
        GetContract.GetOutputContract getOutputContract = new GetContract.GetOutputContract();
        try{
        BufferedReader br_list = new BufferedReader(new FileReader(fileName));
        String st;
        while ((st = br_list.readLine()) != null)
            System.out.println(st);
        getOutputContract.equals(st);
        }catch (IOException e){
            System.out.println(e);
        }
        return getOutputContract;
    }
}