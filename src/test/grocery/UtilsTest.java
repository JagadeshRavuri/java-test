package grocery;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;


public class UtilsTest {

    @Test
    public void checkPromotionValidNow() {
        assertTrue(Utils.isValidOffer(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), LocalDateTime.now()));
    }
}
