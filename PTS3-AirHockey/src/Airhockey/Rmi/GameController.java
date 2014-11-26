package Airhockey.Rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Roel
 */
public class GameController extends UnicastRemoteObject implements IGameController {
    
    private float puckPositionX = 1;
    private float puckPositionY = 2;
    
    public GameController() throws RemoteException {
    }
    
    public float getPuckPositionX() {
        return puckPositionX;
    }
    
    public float getPuckPositionY() {
        return puckPositionY;
    }
}
