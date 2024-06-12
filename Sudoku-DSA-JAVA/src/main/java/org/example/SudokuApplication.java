package org.example;

import BuildLogic.SudokuBuildLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import user_Interface.IUserInterfaceContract;
import user_Interface.interface_impl;

import java.io.IOException;

public class SudokuApplication extends Application
{
    private IUserInterfaceContract.View uiImpl;

    public static void main(String[] strings)
    {
        launch(strings);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        uiImpl = new interface_impl(primaryStage);
        try
        {
            SudokuBuildLogic.build(uiImpl);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
}
