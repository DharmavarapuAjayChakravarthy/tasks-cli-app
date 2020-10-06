public interface TaskServices {
       AddContract.AddOutputContract add(AddContract.AddInputContract addInputContract) ;
       DeleteContract.DeleteOutputContract delete(DeleteContract.DeleteInputContract deleteInputContract) ;
       ModifyContract.ModifyOutputContract modify(ModifyContract.ModifyInputContract modifyInputContract) ;
       GetContract.GetOutputContract show(GetContract.GetInputContract getInputContract);
}
