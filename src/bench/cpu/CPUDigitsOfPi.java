package bench.cpu;

import bench.IBenchmark;
import java.math.BigInteger;

public class CPUDigitsOfPi implements IBenchmark {

    public int limit;
    final BigInteger TWO = BigInteger.valueOf(2);
    final BigInteger THREE = BigInteger.valueOf(3);
    final BigInteger FOUR = BigInteger.valueOf(4);
    final BigInteger SEVEN = BigInteger.valueOf(7);

    BigInteger q = BigInteger.ONE;
    BigInteger r = BigInteger.ZERO;
    BigInteger t = BigInteger.ONE;
    BigInteger k = BigInteger.ONE;
    BigInteger n = BigInteger.valueOf(3);
    BigInteger l = BigInteger.valueOf(3);

    @Override
    public void run() {


        pi(TWO, THREE, FOUR, SEVEN, q, r, t, k, n, l, 100);
    }

    static void pi(BigInteger TWO, BigInteger THREE, BigInteger FOUR, BigInteger SEVEN, BigInteger q, BigInteger r, BigInteger t, BigInteger k, BigInteger n, BigInteger l, int LIMIT) {
        BigInteger nn, nr;
        boolean first = true;
        int counter = 0;
        while (counter < LIMIT) {
            if (FOUR.multiply(q).add(r).subtract(t).compareTo(n.multiply(t)) < 0) {
              //  System.out.print(n);
                counter++;
                if (first) {
             //       System.out.print(".");
                    first = false;
                }
                nr = BigInteger.TEN.multiply(r.subtract(n.multiply(t)));
                n = BigInteger.TEN.multiply(THREE.multiply(q).add(r)).divide(t).subtract(BigInteger.TEN.multiply(n));
                q = q.multiply(BigInteger.TEN);
                r = nr;
                System.out.flush();
            } else {
                nr = TWO.multiply(q).add(r).multiply(l);
                nn = q.multiply((SEVEN.multiply(k))).add(TWO).add(r.multiply(l)).divide(t.multiply(l));
                q = q.multiply(k);
                t = t.multiply(l);
                l = l.add(TWO);
                k = k.add(BigInteger.ONE);
                n = nn;
                r = nr;
            }

        }
    }

    @Override
    public void run(Object... options) {
        this.limit = (int) options [0];

        pi(TWO, THREE, FOUR, SEVEN, q, r, t, k, n, l, limit);

    }

    @Override
    public void initialize(Object... params) {

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup() {

        System.out.println("Starting warmup...\n");

        for(int i = 0; i < 30; i++){
            pi(TWO, THREE, FOUR, SEVEN, q, r, t, k, n, l, 1000);
        }

        System.out.println("Warmup complete!\n");

    }

    @Override
    public String getResult() {
        return null;
    }
}
