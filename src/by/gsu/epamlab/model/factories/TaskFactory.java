package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.impl.TaskImplDB;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

public class TaskFactory {
    private TaskFactory(){}

    public static ITaskDAO getClassFromFactory()
    {
        return new TaskImplDB();
    }
}
