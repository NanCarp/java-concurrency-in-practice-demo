package chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by NanCarp on 2017/5/5.
 */
// @ThreadSafe
public class StatelessFactorizer implements Servlet{

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }

}
