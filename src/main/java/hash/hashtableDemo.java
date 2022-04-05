package hash;

import java.util.Hashtable;
import java.util.Scanner;

public class hashtableDemo {

    public static void main(String[] args) {
        //直接测试
        //doOne();

        //scanner测试
        doTwo();

    }

    private static void doTwo() {

        HashTab hashTab=new HashTab(8);


        String key="";
        Scanner scanner=new Scanner(System.in);
        while (true){

            System.out.println("***************菜单***************");
            System.out.println("a|add:   添加雇员");
            System.out.println("l|list:  展示雇员");
            System.out.println("f|find:  查找雇员");
            System.out.println("d|del:   雇员");
            System.out.println("e|exit:  退出系统");
            System.out.println("***************菜单***************");

            key=scanner.next();
            switch (key){
                case "a":
                case "add":
                    System.out.println("进入添加雇员页");
                    System.out.println("请输入id");
                    int idIn = scanner.nextInt();
                    System.out.println("请输入name");
                    String nameIn = scanner.next();

                    hashTab.add(new Emp(idIn,nameIn));
                    System.out.println("添加成功!!");
                    break;

                case "l":
                case "list":
                    System.out.println("进入显示雇员页");
                    hashTab.list();
                    break;
                case "f":
                case "find":
                    System.out.println("进入查找雇员页");
                    System.out.println("请输入查找雇员的id");
                    //System.out.println("请输入查找雇员的name");
                    int idSearchIn = scanner.nextInt();

                    Emp emp = hashTab.findById(idSearchIn);
                    System.out.println("找到! 雇员为"+emp.toString());
                    break;
                case "d":
                case "del":
                    System.out.println("进入删除雇员页");
                    System.out.println("请输入id");
                    int idDelInt = scanner.nextInt();

                    hashTab.delById(idDelInt);

                    break;
                case "e":
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }

        }

    }

    private static void doOne() {
        HashTab hashTab=new HashTab(8);
        hashTab.add(new Emp(2,"cxk"));
        hashTab.add(new Emp(10,"cxk"));
        hashTab.add(new Emp(18,"cxk"));

        hashTab.add(new Emp(12,"nmb"));
        hashTab.add(new Emp(20,"nmb"));
        hashTab.add(new Emp(28,"nmb"));
        hashTab.list();
    }

}

class HashTab{

    public EmpLinkedList[] empLinkedListArray;
    public int size;

    public HashTab(int size) {
        this.size=size;
        empLinkedListArray=new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();

        }
    }

    public void add(Emp emp){
        int no = getNo(emp);
        empLinkedListArray[no].add(emp);
    }

    public int getNo(Emp emp){
        int no=emp.id%size;
        return no;
    }

    public int getNo(int id){
        int no=id%size;
        return no;
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }

    }

    public Emp findById(int id) {
        int no = getNo(id);
        EmpLinkedList empLinkedList=empLinkedListArray[no];
        if(empLinkedList.head==null){
            System.out.println("找不到该雇员!");
        }
        Emp tmpEmp=empLinkedList.head;
        while (true){
            if (tmpEmp.id==id){
                return tmpEmp;
            }

            if(tmpEmp.next==null){
                return null;
            }
            tmpEmp=tmpEmp.next;
        }
    }

    public void delById(int idDelInt) {

        int tmpId = getNo(idDelInt);
        empLinkedListArray[tmpId].del(idDelInt);
    }
}

class Emp{

    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList{

    public Emp head;

    public void add(Emp emp){

        if(head==null){
            head=emp;
        }else {
            //实现从低到高
            Emp tmpEmp=head;

            Emp preEmp=head;

            while (true){
                if(emp.id<tmpEmp.id){
                    if(tmpEmp==head){
                        emp.next=tmpEmp;
                        head=emp;
                    }else {
                        //非head
                        emp.next=preEmp.next;
                        preEmp.next=emp;
                    }
                    break;
                }
                if(tmpEmp.next==null){
                    tmpEmp.next=emp;
                    break;
                }
                preEmp=tmpEmp;
                tmpEmp=tmpEmp.next;

            }



            /*Emp tmpEmp=head;
            while (tmpEmp.next!=null){
                tmpEmp=tmpEmp.next;
            }
            tmpEmp.next=emp;*/
        }
    }

    public void list(int num){
        if(head==null){
            System.out.println(num+" 链表为空");
        }else {
            Emp tmpEmp=head;
            System.out.print(num+" 链表");

            while (true){
                System.out.print("=> id:"+tmpEmp.id+" name:"+tmpEmp.name);

                if(tmpEmp.next==null){
                    System.out.println();
                    break;
                }
                tmpEmp=tmpEmp.next;
            }
        }
    }

    public void del(int idDelInt) {
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        Emp tmpEmp=head;
        Emp preEmp=tmpEmp;
        while(true){

            if (tmpEmp.id==idDelInt){
                //末尾删除
                if(tmpEmp.next==null){
                    preEmp.next=null;
                }else {//非末尾 拼接
                    preEmp.next=tmpEmp.next;
                }
                System.out.println("删除成功");
                break;
            }

            if(tmpEmp.next==null){
                System.out.println("没找到该雇员");
            }

            preEmp=tmpEmp;
            tmpEmp=tmpEmp.next;
        }
    }
}