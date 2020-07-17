package main.java.Spring.AOP;

public aspect Criticzz {
    public Criticzz() {}
    pointcut performance() :execution(* perform(..));

    after() returning : performance() {
        System.out.println(criticismEngine.getCriticism());
    }
    private CriticismEngine criticismEngine;
    public void setCriticismEngine(CriticismEngine criticismEngine){
        this.criticismEngine = criticismEngine;
    }

}
