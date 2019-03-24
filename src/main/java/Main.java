import ConfigurationManagement.SerializerDeserializerClassMismatchException;
import MainApplication.MainApplication;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, SerializerDeserializerClassMismatchException {
        new MainApplication(args);
    }
}
