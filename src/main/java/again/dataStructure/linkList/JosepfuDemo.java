package again.dataStructure.linkList;

public class JosepfuDemo {

    public static void main(String[] args) {
        CircleLinkedList circleLinkedList=new CircleLinkedList();
        circleLinkedList.addBoy(3);
        circleLinkedList.list();
        System.out.println();
    }
}

class CircleLinkedList{
    //初始的时候id 为-1 而且不指向别人
    Boy first=new Boy(-1);

    public void addBoy(int nums){//增加nums个节点
        if(nums<1){
            System.out.println("小于1是要加多少个?");
        }else {
            int index=0;

            while (index<nums){
                index++;
                Boy curB=first;
                while (curB.next!=first && curB.next!=null){
                    curB=curB.next;
                }

                Boy boy = new Boy(index);

                curB.next=boy;
                boy.next=first;

            }

        }

    }

    public void list() {
            Boy curB=first;
            while (curB.next!=first && curB.next!=null){
                curB=curB.next;
                System.out.print(curB+"->");
            }





    }

}

class Boy{

    int no;
    Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}