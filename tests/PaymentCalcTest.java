import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCalcTest {
    @Test
    public void paymentCalcTest(){
        var mockAge = 69;
        var isPassive = true;

        int paymentAmt = 1800;
        if (mockAge < 18) paymentAmt = 1600;
        if (isPassive) paymentAmt = 500;
        if(mockAge > 59)  paymentAmt = (int) (paymentAmt * 0.75);

        System.out.println(paymentAmt);

        var actualValue = 375;

        assertEquals(actualValue, paymentAmt);
    }
}
