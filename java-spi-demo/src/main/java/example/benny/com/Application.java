package example.benny.com;

import java.util.ServiceLoader;

public class Application {

    public static void main(String[] args) {
        ServiceLoader.load(Application.class);
    }
}
