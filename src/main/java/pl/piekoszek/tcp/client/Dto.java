package pl.piekoszek.tcp.client;

public class Dto {
    public String password;
    public String name;
    public String _id;
    public String _class;

    @Override
    public String toString() {
        return "Dto{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
//                ", _id='" + _id + '\'' +
                ", _class='" + _class + '\'' +
                '}';
    }
}