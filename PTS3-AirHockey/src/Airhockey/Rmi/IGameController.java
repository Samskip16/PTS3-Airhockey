package Airhockey.Rmi;

import java.rmi.RemoteException;

/**
 *
 * @author Roel
 */
public interface IGameController extends java.rmi.Remote {

    float getPuckPositionX() throws RemoteException;

    float getPuckPositionY() throws RemoteException;

}
