package cli;

import org.junit.jupiter.api.Test;

class cliInterfaceTest {
    @Test
    public void greetTest(){
        CliInterface cliInterface = new CliInterface();
        cliInterface.greet();
    }
}