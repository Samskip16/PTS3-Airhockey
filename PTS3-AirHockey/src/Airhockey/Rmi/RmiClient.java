package Airhockey.Rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Roel
 */
public class RmiClient {

    // Set binding name for game controller
    private static final String bindingName = "GameController";

    // References to registry and gamecontroller
    private Registry registry = null;
    private IGameController gameController = null;

    // Constructor
    public RmiClient(String ipAddress, int portNumber) {

        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Print contents of registry
        if (registry != null) {
            printContentsRegistry();
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                gameController = (IGameController) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind game controller");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                gameController = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind game controller");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                gameController = null;
            }
        }

        // Print result binding student administration
        if (gameController != null) {
            System.out.println("Client: Game controller bound");
        } else {
            System.out.println("Client: Game controller is null pointer");
        }

        try {
            System.out.println(gameController.getPuckPositionX());
            System.out.println(gameController.getPuckPositionY());
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {

        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String ipAddress = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        int portNumber = input.nextInt();

        // Create client
        RmiClient client = new RmiClient(ipAddress, portNumber);
    }
}
