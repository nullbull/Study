package Java.RTTI;

import java.util.ArrayList;

class Person {
    public final String first;
    public final String last;
    public final String address;
    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }
    public String toString() {
        return "Person" + first + " " + last + " " + address;
    }
   public static class NullPerson extends Person  {
        private NullPerson() {super("None" ,"None", "None");}

        @Override
        public String toString() {
            return "NonePerson";
        }
    }
    public static final Person NULL = new NullPerson();
}


class Positon {
    private String title;
    private Person person;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (this.person == null)
            this.person = Person.NULL;
    }

    public Positon(String title, Person person) {
        this.title = title;
        this.person = person;
        if (this.person == null)
            this.person = Person.NULL;
    }

    public Positon(String title) {
        this.title = title;
        this.person = Person.NULL;
    }

    @Override
    public String toString() {
        return "Position: " + title + " " + person;
    }
}
    class Staff extends ArrayList<Positon> {
        public void add(String title, Person person) {
            add(new Positon(title, person));
        }
        public void add(String... titles) {
            for(String title : titles)
                add(new Positon(title));
        }
        public Staff(String...titles) {
            add(titles);
        }
        public boolean positionAvailable(String title) {
            for(Positon positon :this)
                if(positon.getTitle().equals(title) &&
                        positon.getPerson() == Person.NULL)
                    return true;
            return false;
        }
        public void fillPosition(String title, Person hired) {
            for(Positon positon : this)
                if(positon.getTitle().equals(title) &&
                        positon.getPerson() == Person.NULL){
                    positon.setPerson(hired);
                    return;
                }
                throw new RuntimeException("Position" + title + " not available");
        }
        public int getSize() {
            System.out.println(this);
            return this.size();
        }

        public static void main(String[] args) {
            Staff staff = new Staff("President", "CTO", "Marketing Manager"
            ,"Porduct Manager", "Project Lead");
            staff.fillPosition("CTO", new Person("niu", "zhenhao", "handsome"));
           System.out.println(staff.getSize());
        }
    }

