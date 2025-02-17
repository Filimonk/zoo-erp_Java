package zoo;

public abstract class ZooEntity implements Comparable<ZooEntity> {
    protected String name;
    public abstract String getName();
    
    @Override
    public int compareTo(ZooEntity other) {
        return Integer.compare(System.identityHashCode(this), System.identityHashCode(other));
    }
}

