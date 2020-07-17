package Spring.AOP;

public class CriticismEngineImpl {
    String[] criticismPool;

    public String getCriticism() {
        return criticismPool[(int) (Math.random()*criticismPool.length)];
    }

    public void setCriticismPool(String[] criticismPool) {
        this.criticismPool = criticismPool;
    }
}
