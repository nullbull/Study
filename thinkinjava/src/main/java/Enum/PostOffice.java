package main.java.Enum;

import java.util.Iterator;

class Mail {
    enum GeneraDelivery {yes, no1, no2, no3, no4, no5}
    enum Scannability {unscannable, yes1, yes2, yes3, yes4}
    enum Readability {illegible,  yes1, yes2, yes3, yes4}
    enum Address {incorrecy,ok1, ok2, ok3, ok4, ok5, ok6}
    enum ReturnAddress{missing, ok1, ok2, ok3, ok4, ok5}
    GeneraDelivery generaDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter++;

    @Override
    public String toString() {
        return "Mail " + id;
    }
    public String details() {
        return toString() +
                ", General Delivery:" + generaDelivery +
                ", Address Scannability:" + scannability +
                ", Address Readability:" + readability +
                ", Address Address:" + address +
                ", Return address:" + returnAddress;
    }
    public static Mail randomMail() {
        Mail m = new Mail();
        m.generaDelivery = Enums.random(GeneraDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }
    public static Iterable<Mail> generator (final int count) {
        return new Iterable<Mail>() {
            int n = count;
            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }
                    @Override
                    public Mail next() {return randomMail();}
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }

                };
            }
        };
    }

}
public class PostOffice {
    enum MailHandler {
        General_Delivery {
            boolean handle(Mail m) {
                switch (m.generaDelivery) {
                    case yes:
                        System.out.println("Using general delivery for " + m);
                        return true;

                    default:
                        return false;
                }
            }
        },
        Machine_Scan {
            boolean handle(Mail m) {
                switch (m.scannability) {
                    case unscannable:
                        return false;
                    default:
                        switch (m.address) {
                            case incorrecy:
                                return false;
                            default:
                                System.out.println("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        },
        Visual_Inspection {
            boolean handle(Mail m) {
                switch (m.readability) {
                    case illegible: return false;
                    default:
                        switch (m.address) {
                            case incorrecy:
                                return false;
                            default:
                                System.out.println("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        },
        Return_To_Sender {
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case missing: return false;
                    default:
                        System.out.println("Returing " + m + "to sender");
                        return true;
                }
            }
        };



        abstract boolean handle(Mail mail);
        }
        static void handle(Mail m) {
            for (MailHandler mailHandler : MailHandler.values())
                if(mailHandler.handle(m))
                    return;
            System.out.println(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            System.out.println(mail.details());
            handle(mail);
            System.out.println("**************");
        }
    }
}
