package test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Auth justinniu
 * @Date 2018/8/24
 * @Desc
 */
public class Mutx {
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        public boolean tryAcquire(int ac) {
            if (compareAndSetState(0, ac)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        public boolean tryRelease(int re) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(re);
            return true;
        }

    }
    private final Sync sync = new Sync();

    public void lock() {
        sync.tryAcquire(1);
    }
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    public void unLock() {
        sync.tryAcquire(0);
    }
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }


    static int a = 0;

}
