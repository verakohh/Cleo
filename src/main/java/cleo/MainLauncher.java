package cleo;

/**
 * The launcher for the Cleo application.
 * This class is used to work around issues where directly running a class
 * that extends Application may fail on certain platforms (e.g., Windows and Linux).
 */
public class MainLauncher {

    /**
     * The entry point of the Cleo application.
     * This method delegates the call to the Main class, which extends Application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Main.main(args); // Call the actual Application class
    }
}
