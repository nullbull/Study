package Spring.AOP;

public aspect Audienc {
    private Worker worker;
    public Audienc(){}
    public void setWorker(Worker worker){
        this.worker = worker;
        System.out.printf("工作人员入场");
    }
    pointcut piano():execution(PianoPerform.new());
    after():piano() {
        worker.sendMsg("钢琴");
    }
    pointcut violin():execution(ViolinPerform.new());
    after():violin() {
        worker.sendMsg("小提琴");
    }
    //定义不带参数方法切点和前置通知
    pointcut perform():execution(* concert.Performance.perform());
    before():perform(){
        worker.take();
    }

    //定义带两个参数的切点和后置通知
    pointcut finishPerform(String performer, String title):execution(* concert.Performance.finishPerform(String, String)) && args(performer, title);
    after(String performer, String title):finishPerform(performer, title){
        worker.broadcast(performer, title);
    }

}
