import java.util.*;


interface Aggregate {
    public void add(Object obj);
    public void remove(Object obj);
    public Iterator getIterator();
}

class ConcreteAggregate implements Aggregate {
    private List<Object> list=new ArrayList<Object>();
    public void add(Object obj) {
        list.add(obj);
    }
    public void remove(Object obj) {
        list.remove(obj);
    }
    public Iterator getIterator() {
        return(new ConcreteIterator(list));
    }
}

interface Iterator {
    Object first();
    Object next();
    Object previous();
    Object last();
    boolean hasNext();
}

class ConcreteIterator implements Iterator {
    private List<Object> list=null;
    private int index=-1;
    public ConcreteIterator(List<Object> list) {
        this.list=list;
    }
    public boolean hasNext() {
        if(index<list.size()-1) {
            return true;
        }
        else {
            return false;
        }
    }
    public Object first() {
        index=0;
        Object obj=list.get(index);;
        return obj;
    }
    public Object next() {
        Object obj=null;
        if(this.hasNext()) {
            obj=list.get(++index);
        }
        return obj;
    }
    public Object previous() {
        Object obj=null;
        if(this.hasNext()) {
            obj=list.get(--index);
        }
        return obj;
    }
    public Object last() {
        index=list.size()-1;
        Object obj = list.get(index);
        return obj;
    }
}

public class IteratorDesignPatterEx{
    public static void main(String[] args) {
        Aggregate ag = new ConcreteAggregate();
        ag.add("Jianguo Middle School");
        ag.add("High School Attached to Normal University");
        ag.add("success high school");
        ag.add("Matsuyama High School");
        ag.add("Zhonglun High School");
        ag.add("Lishan High School");
        System.out.print("School List：");
        Iterator it = ag.getIterator();
        while(it.hasNext()) {
            Object ob=it.next();
            System.out.print(ob.toString()+"\t");
        }
        Object ob=it.first();
        System.out.println("\nFirst："+ob.toString());

        Scanner scanner = new Scanner(System.in);

        boolean end = false;

        while (!end){
            System.out.println("Please enter（1：First:2：Next:3：Final）：");
            int num = scanner.nextInt();
            if (num == 1){
                System.out.println("School：" +  it.first().toString());
            } else if (num == 2){
                if (it.hasNext()){
                    System.out.println("School：" +  it.next().toString());
                } else {
                    System.out.println("has reached the last");
                }
            } else if (num == 3){
                System.out.println("School：" +  it.last().toString());
            } else {
                System.out.println("Please enter the correct command" );
            }
        }
    }
}
