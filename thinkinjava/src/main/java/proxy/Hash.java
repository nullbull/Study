package test;

/**
 * @Auth justinniu
 * @Date 2018/8/24
 * @Desc
 */
public class Hash {
    private int id;
    private String name;
    private boolean sex;

    public Hash(int id, String name, boolean sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + id;
        result = result * 31 + name.hashCode();
        return  result * 31 + (sex ? 0 : 1);
    }

    @Override
    public boolean equals(Object obj) {
        boolean mark = false;
        if (obj instanceof Hash) {
            if (id == ((Hash) obj).id && name == ((Hash) obj).name && sex == ((Hash) obj).sex)
                mark = true;
        }
        return mark;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public static void main(String[] args) {
        Hash niu = new Hash(10, "niu", false);
        Hash zhang = new Hash(10, "zhang", true);
        System.out.println(niu.hashCode());
        System.out.println(zhang.hashCode());
        niu.setName("zhang");
        niu.setSex(true);
        System.out.println(niu.hashCode());
        System.out.println(niu.equals(zhang));
    }
}
