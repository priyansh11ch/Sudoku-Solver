package BuildLogic;

import computationLogic.GameLogic;
import org.example.IStorage;
import org.example.Game;
import persistance.LocalStorageImpl;
import user_Interface.IUserInterfaceContract;
import user_Interface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic
{
    public static void build(IUserInterfaceContract.View userInterface) throws IOException
    {
        Game initialState;
        IStorage storage = new LocalStorageImpl();

        try
        {
            initialState = storage.getGameData();
        }
        catch (IOException e)
        {
            initialState = GameLogic.getNewGame();
            storage.upadteGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic  = new ControlLogic(storage,userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
