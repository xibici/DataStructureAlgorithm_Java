package again.dataStructure.hashTab;

import java.util.Arrays;
import java.util.UUID;

public class hashTableDemoAG {
    public static void main(String[] args) {


        HashTableAg hashTableAg=new HashTableAg(5);
        //System.out.println(hashTableAg);

        hashTableAg.addByIdOrder(new Emp(6, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(1, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(61, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(56, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(67, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(31, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(83, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(93, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(33, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(3, UUID.randomUUID().toString().substring(0,4)));
        hashTableAg.addByIdOrder(new Emp(6, UUID.randomUUID().toString().substring(0,4)));

        hashTableAg.list();
        Emp emp = hashTableAg.findEmpById(67);
        System.out.println(emp);
    }

}

class HashTableAg{

    EmpLinkedListHead[] empLinkedListHead;

    public HashTableAg(int num) {
        //只创建了长度个的null
        empLinkedListHead=new EmpLinkedListHead[num];
        for (int i = 0; i < num; i++) {
            empLinkedListHead[i]=new EmpLinkedListHead();
        }
    }

    public int getTrueIndex(int index){
        int addIndex=index% empLinkedListHead.length;
        return addIndex;
    }

    public void addLast(Emp emp){
        empLinkedListHead[getTrueIndex(emp.id)].addLast(emp);
    }

    public void addByIdOrder(Emp emp){
        empLinkedListHead[getTrueIndex(emp.id)].addByIdOrder(emp);
    }

    public void list() {
        /*EmpLinkedListHead empLinkedListHead = this.empLinkedListHead[0];
        empLinkedListHead.list();*/
        for (int i = 0; i < empLinkedListHead.length; i++) {
            System.out.print(i+"->");
            EmpLinkedListHead empLinkedListHead = this.empLinkedListHead[i];
            empLinkedListHead.list();
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "HashTableAg{" +
                "empLinkedListHead=" + Arrays.toString(empLinkedListHead) +
                '}';
    }

    public Emp findEmpById(int id){
        int trueIndex = getTrueIndex(id);
        return empLinkedListHead[trueIndex].findEmpById(id);
    }
}

class EmpLinkedListHead{

    Emp head;

    public void list(){
        if(head==null){
            System.out.print("该链表为空");
        }
        Emp tmp=head;
        while (tmp!=null){
            System.out.print(tmp+"->");
            tmp=tmp.next;
        }
    }

    public void addLast(Emp emp){
        if(head==null){
            head=emp;
        }else {
            Emp tmp=head;
            while (tmp.next!=null){
                tmp=tmp.next;
            }
            tmp.next=emp;
        }
    }

    public void addByIdOrder(Emp emp) {
        if(head==null){
            head=emp;
            return;
        }

        int eId =emp.id;
        Emp tmp=head;
        if(eId<head.id){
            emp.next=tmp;
            head=emp;
            return;
        }

        while (tmp.next!=null){
            if(eId<tmp.next.id){
                emp.next=tmp.next;
                tmp.next=emp;
                return;
            }else {
                tmp=tmp.next;
            }
        }

        tmp.next=emp;

    }

    public Emp findEmpById(int id) {
        if(head==null){
            System.out.println("列表为空");
            return null;
        }
        Emp tmp=head;
        while (tmp!=null){
            if(tmp.id==id){
                return tmp;
            }
            tmp= tmp.next;
        }

        //最终查不到返回null
        return null;
    }
}


class Emp{

    int id;
    String name;
    Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ",name:" + name  +
                '}';
    }
}
