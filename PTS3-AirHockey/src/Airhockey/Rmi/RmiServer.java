package Airhockey.Rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Roel
 */
public class RmiServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for game controller
    private static final String bindingName = "GameController";

    // References to registry and game controller
    private Registry registry = null;
    private GameController gameController = null;

    public RmiServer() {
        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        // Create game controller
        try {
            gameController = new GameController();
            System.out.println("Server: Game controller created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create game controller");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            gameController = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind game controller using registry
        try {
            registry.rebind(bindingName, gameController);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind game controller");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        RmiServer rmiServer = new RmiServer();
    }
}
