package persistence;

import model.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTestSuper {
    protected void checkStock(String n, int up, int ia, String time, Stock st) {
        assertEquals(n, st.getName());
        assertEquals(up, st.getUnitPrice());
        assertEquals(ia, st.getInvestedAmount());
        assertEquals(time, st.getTime());
    }
}
