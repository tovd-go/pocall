package rmi2;

import java.io.IOException;

public interface ExecService {
    public String exec(String message) throws IOException;
}
