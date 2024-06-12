package user_Interface.logic;

import Constants.GameState;
import Constants.Messages;
import computationLogic.GameLogic;
import org.example.IStorage;
import org.example.Game;
import user_Interface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener
{
    private IStorage storage;
    private IUserInterfaceContract.View view;
    private Game gameData;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view)
    {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
       try
       {
           Game g = storage.getGameData();
           int[][] newGridState = gameData.getCopyofGridState();
           newGridState[x][y] = input;
           
           gameData = new Game(
                   GameLogic.checkForCompletion(newGridState),
                   newGridState
           );
           storage.upadteGameData(gameData);
           view.updateSquare(x,y,input);
           
           if(gameData.getGamestate()== GameState.COMPLETE)
           {
               view.showDialog(Messages.GAME_COMPLETE);
           }
       }
       catch(IOException e)
       {
           e.printStackTrace();
           view.showError(Messages.ERROR);
       }
    }

    @Override
    public void onDialogClick() {
        try
        {
            storage.upadteGameData(
                    GameLogic.getNewGame()
            );
            view.updateBoard(storage.getGameData());
        }
        catch(IOException e)
        {
            view.showError(Messages.ERROR);
        }
    }
}
